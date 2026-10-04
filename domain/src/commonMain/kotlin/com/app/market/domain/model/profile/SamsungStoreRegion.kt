package com.app.market.domain.model.profile

/** User-selected Galaxy Store catalog. The resolved ODC endpoint remains server-authoritative. */
enum class SamsungStoreRegion {
    CHINA,
    GLOBAL,
}

/** Region attributes that are actually written to the SamsungProtocol envelope. */
data class SamsungRequestContext(
    val countryCode: String,
    val language: String,
    val mcc: String,
    val mnc: String,
    val csc: String,
)

fun SamsungStoreRegion.requestContext(): SamsungRequestContext = when (this) {
    SamsungStoreRegion.CHINA -> SamsungRequestContext(
        countryCode = "CHN",
        language = "zh_CN",
        mcc = "460",
        mnc = "00",
        csc = "CHC",
    )

    SamsungStoreRegion.GLOBAL -> SamsungRequestContext(
        countryCode = "USA",
        language = "en_US",
        mcc = "310",
        mnc = "260",
        csc = "XAA",
    )
}

fun MarketProfile.samsungStoreRegion(): SamsungStoreRegion =
    when {
        co.equals("CN", ignoreCase = true) -> SamsungStoreRegion.CHINA
        lo.startsWith("CN", ignoreCase = true) -> SamsungStoreRegion.CHINA
        else -> SamsungStoreRegion.GLOBAL
    }
