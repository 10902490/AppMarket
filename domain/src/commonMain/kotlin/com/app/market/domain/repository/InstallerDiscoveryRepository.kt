package com.app.market.domain.repository

import com.app.market.domain.model.installer.InstallerCandidate

interface InstallerDiscoveryRepository {
    suspend fun listCandidates(): List<InstallerCandidate>
}
