package com.app.market.data.remote.samsung

import com.app.market.data.local.StringPreferenceKey
import com.app.market.domain.model.profile.SamsungStoreRegion

internal object SamsungRegionPreferenceKeys {
    private const val NS = "samsung_region"

    fun countryUrl(region: SamsungStoreRegion) = StringPreferenceKey(NS, "${region.token()}_country_url")
    fun countryCode(region: SamsungStoreRegion) = StringPreferenceKey(NS, "${region.token()}_country_code")
    fun mcc(region: SamsungStoreRegion) = StringPreferenceKey(NS, "${region.token()}_mcc")
    fun generation(region: SamsungStoreRegion) = StringPreferenceKey(NS, "${region.token()}_generation")

    private fun SamsungStoreRegion.token(): String = name.lowercase()
}
