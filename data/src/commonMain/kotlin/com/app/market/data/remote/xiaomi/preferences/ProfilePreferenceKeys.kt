package com.app.market.data.remote.xiaomi.preferences

import com.app.market.data.local.StringPreferenceKey
import com.app.market.domain.model.market.AppSource
import com.app.market.domain.model.profile.OppoStoreRegion as OppoRegion
import com.app.market.domain.model.profile.SamsungStoreRegion as SamsungRegion

object ProfilePreferenceKeys {
    private const val NS = "market_profile"

    fun source(appSource: AppSource) = StringPreferenceKey(NS, "profile_source_${appSource.token}")
    fun overrides(appSource: AppSource) = StringPreferenceKey(NS, "profile_overrides_${appSource.token}")
    fun currentTemplate(appSource: AppSource) =
        StringPreferenceKey(NS, "profile_current_template_${appSource.token}")

    fun field(appSource: AppSource, name: String) =
        StringPreferenceKey(NS, "profile_field_${appSource.token}_$name")

    val Templates = StringPreferenceKey(NS, "profile_templates")
    val SyncedWebResource = StringPreferenceKey(NS, "synced_web_res_version")
    val SyncedPageConfig = StringPreferenceKey(NS, "synced_page_config_version")
    val LastServerSync = StringPreferenceKey(NS, "last_server_sync_time")
    val OppoStoreRegion = StringPreferenceKey(NS, "oppo_store_region")
    val SamsungStoreRegion = StringPreferenceKey(NS, "samsung_store_region")
    fun oppoUserRegion(region: OppoRegion) = StringPreferenceKey(NS, "oppo_${region.token()}_user_region")
    fun oppoSystemLocale(region: OppoRegion) = StringPreferenceKey(NS, "oppo_${region.token()}_system_locale")
    fun oppoSupportedLocales(region: OppoRegion) =
        StringPreferenceKey(NS, "oppo_${region.token()}_supported_locales")

    fun oppoLocale(region: OppoRegion) = StringPreferenceKey(NS, "oppo_${region.token()}_locale")
    fun samsungCountryCode(region: SamsungRegion) =
        StringPreferenceKey(NS, "samsung_${region.token()}_country_code")

    fun samsungLanguage(region: SamsungRegion) =
        StringPreferenceKey(NS, "samsung_${region.token()}_language")

    fun samsungMcc(region: SamsungRegion) = StringPreferenceKey(NS, "samsung_${region.token()}_mcc")
    fun samsungMnc(region: SamsungRegion) = StringPreferenceKey(NS, "samsung_${region.token()}_mnc")
    fun samsungCsc(region: SamsungRegion) = StringPreferenceKey(NS, "samsung_${region.token()}_csc")

    private fun OppoRegion.token(): String = if (this == OppoRegion.CHINA) "china" else "global"
    private fun SamsungRegion.token(): String = if (this == SamsungRegion.CHINA) "china" else "global"
}
