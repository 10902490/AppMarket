package com.app.market.di

import com.app.market.platform.AndroidPermissionCoordinator
import com.app.market.platform.AndroidUiPlatform
import com.app.market.platform.UiPlatform
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val uiPlatformModule = module {
    singleOf(::AndroidPermissionCoordinator)
    singleOf(::AndroidUiPlatform) { bind<UiPlatform>() }
}
