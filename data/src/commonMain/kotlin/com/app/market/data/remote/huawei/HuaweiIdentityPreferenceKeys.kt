package com.app.market.data.remote.huawei

import com.app.market.data.local.StringPreferenceKey

/** Huawei identities are isolated from Xiaomi and the shared editable device profile. */
internal object HuaweiIdentityPreferenceKeys {
    val InstallId = StringPreferenceKey("huawei_identity", "install_id")
}
