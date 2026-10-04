package com.app.market.domain.repository

import com.app.market.domain.model.installer.SavedPackage

/** Locally saved APK files. */
interface SavedPackageRepository {
    suspend fun list(): List<SavedPackage>
    suspend fun install(id: String)
    suspend fun delete(id: String)
}
