package com.app.market.data.remote.oppo

import com.app.market.di.dataModules
import com.app.market.domain.model.download.DownloadMeta
import com.app.market.domain.model.profile.OppoStoreRegion
import com.app.market.domain.repository.ProfileRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.utils.io.readAvailable
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LiveOppoGlobalSearchTest {
    @Test
    fun globalCatalogFindsXAndDevCheckAndOpensTheirDetails() = runBlocking {
        if (System.getenv(ENABLE_ENV) != "1") return@runBlocking
        val koin = startKoin { modules(dataModules) }.koin
        try {
            val api = koin.get<OppoApi>()
            val client = koin.get<HttpClient>()
            val profiles = koin.get<ProfileRepository>()
            val originalRegion = profiles.currentOppoStoreRegion()
            try {
                profiles.setOppoStoreRegion(OppoStoreRegion.GLOBAL)

                val xResults = api.search("X", page = 0).items
                val x = xResults.firstOrNull { it.packageName == X_PACKAGE }
                assertTrue(x != null, "Global OPPO catalog did not return X: ${xResults.summary()}")

                val devCheckResults = api.search("DevCheck", page = 0).items
                val devCheck = devCheckResults.firstOrNull {
                    it.displayName.equals("DevCheck", ignoreCase = true)
                }
                assertTrue(devCheck != null, "Global OPPO catalog did not return DevCheck: ${devCheckResults.summary()}")

                val xDetail = api.appDetail(x.appId, x.packageName, null)
                val devCheckDetail = api.appDetail(devCheck.appId, devCheck.packageName, null)
                assertEquals(X_PACKAGE, xDetail.app.packageName)
                assertEquals(devCheck.packageName, devCheckDetail.app.packageName)
                assertTrue(xDetail.screenshots.isNotEmpty(), "Global X detail did not expose screenshots")
                assertTrue(devCheckDetail.screenshots.isNotEmpty(), "Global DevCheck detail did not expose screenshots")
                probeDownload(client, api.downloadMeta(xDetail.app), "X")
                probeDownload(client, api.downloadMeta(devCheckDetail.app), "DevCheck")
            } finally {
                profiles.setOppoStoreRegion(originalRegion)
            }
        } finally {
            stopKoin()
        }
    }

    private fun List<com.app.market.domain.model.market.MarketAppInfo>.summary(): String =
        joinToString(limit = 10) { "${it.displayName}<${it.packageName}>" }.ifBlank { "<empty>" }

    private suspend fun probeDownload(client: HttpClient, meta: DownloadMeta, label: String) {
        assertTrue(meta.parts.isNotEmpty(), "$label download metadata had no APK files")
        assertEquals(meta.size, meta.parts.sumOf { it.size }, "$label total download size did not match its files")
        meta.parts.forEachIndexed { index, part ->
            assertTrue(part.url.startsWith("https://"), "$label part $index did not use HTTPS")
            assertTrue(!part.url.contains("/download/overseas/"), "$label part $index was still a metadata URL")
            assertTrue(part.size > 0L, "$label part $index did not have a valid size")
            client.prepareGet(part.url) {
                meta.requestHeaders.forEach { (name, value) -> header(name, value) }
                header(HttpHeaders.AcceptEncoding, "identity")
                header(HttpHeaders.Range, "bytes=0-3")
            }.execute { response ->
                val bytes = ByteArray(4)
                val count = response.bodyAsChannel().readAvailable(bytes)
                assertTrue(response.status.isSuccess(), "$label part $index returned HTTP ${response.status.value}")
                assertEquals(4, count, "$label part $index did not return four APK magic bytes")
                assertTrue(
                    bytes.contentEquals(byteArrayOf(0x50, 0x4b, 0x03, 0x04)),
                    "$label part $index was not an APK/ZIP response: ${bytes.joinToString { "%02x".format(it) }}",
                )
            }
        }
    }

    private companion object {
        const val ENABLE_ENV = "APPMARKET_LIVE_OPPO_GLOBAL_SEARCH"
        const val X_PACKAGE = "com.twitter.android"
    }
}
