package com.app.market.domain.repository

import com.app.market.domain.model.download.DownloadMeta
import com.app.market.domain.model.download.DownloadState
import com.app.market.domain.model.download.DownloadTaskKey
import com.app.market.domain.model.install.DeltaFallback
import com.app.market.domain.model.install.InstallUserAction
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface DownloadRepository {
    val states: StateFlow<Map<String, DownloadState>>
    val taskStates: StateFlow<Map<DownloadTaskKey, DownloadState>>
    val installedPackages: SharedFlow<String>
    val deltaFallbacks: SharedFlow<DeltaFallback>
    val pendingUserAction: StateFlow<InstallUserAction?>
    fun start(meta: DownloadMeta, installAfterDownload: Boolean = true)
    fun install(packageName: String)
    fun cancel(packageName: String)
    fun cancel(packageName: String, versionCode: Long)
    fun clear(packageName: String)
    fun consumePendingUserAction()
}
