package com.app.market.domain.model.profile

/** Region values sent by the official OPPO store alongside the signed request headers. */
data class OppoRequestContext(
    val userRegion: String,
    val systemLocale: String,
    val supportedLocales: String,
    val locale: String,
)

fun OppoStoreRegion.oppoRequestContext(): OppoRequestContext = when (this) {
    OppoStoreRegion.CHINA -> OppoRequestContext(
        userRegion = "CN",
        systemLocale = "zh-CN",
        supportedLocales = "zh-CN",
        locale = "zh-CN;CN",
    )

    // The supplied global-store captures use the Macao catalog and Hong Kong Chinese locale.
    // `user-region` selects the resource catalog; merely changing the API host does not.
    OppoStoreRegion.GLOBAL -> OppoRequestContext(
        userRegion = "MO",
        systemLocale = "zh-HK",
        supportedLocales = "zh-HK",
        locale = "zh-HK;CN",
    )
}
