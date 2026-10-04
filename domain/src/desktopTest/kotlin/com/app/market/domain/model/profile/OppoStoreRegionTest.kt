package com.app.market.domain.model.profile

import kotlin.test.Test
import kotlin.test.assertEquals

class OppoStoreRegionTest {
    @Test
    fun localePrefixIdentifiesChinaWhenCountryIsUnavailable() {
        assertEquals(OppoStoreRegion.CHINA, profile(co = "", lo = "CNEN").oppoStoreRegion())
    }

    @Test
    fun explicitCountryTakesPriorityOverLocale() {
        assertEquals(OppoStoreRegion.GLOBAL, profile(co = "US", lo = "CNEN").oppoStoreRegion())
        assertEquals(OppoStoreRegion.CHINA, profile(co = "cn", lo = "US").oppoStoreRegion())
    }

    private fun profile(co: String, lo: String) = MarketProfile(
        co = co,
        la = "zh",
        lo = lo,
        cpuArchitecture = "arm64-v8a",
        device = "PJJ110",
        model = "PJJ110",
        os = "ColorOS",
        osV2 = "ColorOS",
        androidVersion = "15",
        sdk = "35",
        resolution = "1080*2412",
        densityDpi = "480",
        densityScaleFactor = "3.0",
        miuiBigVersionCode = "",
        miuiBigVersionName = "",
        osBigVersionCode = "15",
        osBigVersionName = "15",
        marketVersion = "122280",
        pageConfigVersion = "",
        webResVersion = "",
        hybridFrameworkVersion = "",
        buildId = "build",
        instanceId = "instance",
        hasGMSCore = "true",
        supportedIslandVersion = "1",
    )
}
