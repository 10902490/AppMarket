package com.app.market.ui.util

import androidx.compose.runtime.Composable
import com.app.market.platform.UiPlatform
import com.app.market.resources.Res
import com.app.market.resources.download
import com.app.market.resources.install
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

/** 平台不支持直接安装（桌面端）时，「安装」动作实际是保存安装包，文案改用「下载」。 */
@Composable
fun installActionText(): String {
    val uiPlatform = koinInject<UiPlatform>()
    return stringResource(
        if (uiPlatform.packageInstallationSupported) Res.string.install else Res.string.download,
    )
}
