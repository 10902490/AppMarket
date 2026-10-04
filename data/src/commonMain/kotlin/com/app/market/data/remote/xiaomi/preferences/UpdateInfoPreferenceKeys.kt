package com.app.market.data.remote.xiaomi.preferences

import com.app.market.data.local.StringPreferenceKey

object UpdateInfoPreferenceKeys {
    private const val NS = "market_update_info"
    val InvalidSystemPackageHash = StringPreferenceKey(NS, "invalid_system_package_hash")
}
