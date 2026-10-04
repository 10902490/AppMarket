package com.app.market.di

import com.app.market.platform.DesktopUiPlatform
import com.app.market.platform.UiPlatform
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val uiPlatformModule = module {
    singleOf(::DesktopUiPlatform) { bind<UiPlatform>() }
}
