package com.app.market.domain.model.update

import com.app.market.domain.model.market.MarketAppInfo

data class ManualUpdateResult(
    val status: ManualUpdateStatus,
    val app: MarketAppInfo? = null,
)
