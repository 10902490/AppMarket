package com.app.market.data.remote.oppo

import com.app.market.di.dataModules
import com.app.market.domain.model.installed.InstalledPackage
import com.app.market.domain.model.market.AppSource
import com.app.market.domain.model.profile.OppoStoreRegion
import com.app.market.domain.repository.ProfileRepository
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class LiveOppoGlobalUpdateTest {
    @Test
    fun probesDevCheckUpdate() = runBlocking {
        if (System.getenv(ENABLE_ENV) != "1") return@runBlocking
        val koin = startKoin { modules(dataModules) }.koin
        try {
            val api = koin.get<OppoApi>()
            val profiles = koin.get<ProfileRepository>()
            val originalRegion = profiles.currentOppoStoreRegion()
            try {
                profiles.setOppoStoreRegion(OppoStoreRegion.GLOBAL)
                val profile = profiles.load(AppSource.OPPO)
                val update = api.checkUpdates(
                    installed = listOf(
                        InstalledPackage(
                            packageName = "flar2.devcheck",
                            versionCode = 628L,
                            versionName = "6.28",
                            isSystemApp = false,
                            targetSdkVersion = 35,
                            splits = "1:[config.arm64_v8a#config.xxhdpi]",
                            baseApkPath = "/data/app/flar2.devcheck/base.apk",
                            signature = "248585BC841C79FE82D405C65F21B987",
                            signatureList = listOf("edd3fef236fd0b7dfc081a7c3fa56858"),
                            installOrigin = "com.android.packageinstaller",
                        )
                    ),
                    profile = profile,
                ).singleOrNull { it.packageName == "flar2.devcheck" }
                assertNotNull(update, "Global OPPO update endpoint did not return the installed DevCheck build")
                assertEquals(650L, update.versionCode)
            } finally {
                profiles.setOppoStoreRegion(originalRegion)
            }
        } finally {
            stopKoin()
        }
    }

    private companion object {
        const val ENABLE_ENV = "APPMARKET_LIVE_OPPO_GLOBAL_UPDATE"
    }
}
