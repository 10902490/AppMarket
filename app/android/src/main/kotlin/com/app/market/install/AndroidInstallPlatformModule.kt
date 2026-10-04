package com.app.market.install

import com.app.market.data.install.platform.ExternalInstallerLauncher
import com.app.market.data.install.platform.InstallStatusIntentFactory
import com.app.market.data.install.platform.InstallTaskLauncher
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val androidInstallPlatformModule = module {
    singleOf(::InstallTaskLauncherImpl) { bind<InstallTaskLauncher>() }
    singleOf(::InstallStatusIntentFactoryImpl) { bind<InstallStatusIntentFactory>() }
    singleOf(::ExternalInstallerLauncherImpl) { bind<ExternalInstallerLauncher>() }
    singleOf(::XmsfFirewallGate)
    singleOf(::NotificationIconLoader)
    singleOf(::InstallNotificationCoordinator)
}
