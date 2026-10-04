package com.app.market.domain.model.profile

enum class OppoStoreRegion {
    CHINA,
    GLOBAL,
}

fun MarketProfile.oppoStoreRegion(): OppoStoreRegion =
    when {
        co.isNotBlank() -> if (co.equals("CN", ignoreCase = true)) OppoStoreRegion.CHINA else OppoStoreRegion.GLOBAL
        lo.startsWith("CN", ignoreCase = true) -> OppoStoreRegion.CHINA
        else -> OppoStoreRegion.GLOBAL
    }
