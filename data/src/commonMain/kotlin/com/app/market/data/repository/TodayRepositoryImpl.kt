package com.app.market.data.repository

import com.app.market.data.remote.xiaomi.XiaomiApi
import com.app.market.domain.model.today.TodayArticle
import com.app.market.domain.model.today.TodayFeedPage
import com.app.market.domain.repository.AccountRepository
import com.app.market.domain.repository.ProfileRepository
import com.app.market.domain.repository.TodayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Platform-agnostic Today / 金米奖 repository: all networking/parsing lives in commonMain ([XiaomiApi]).
 * The platform supplies the device [profileStore] and account [cookies] (empty = anonymous on desktop).
 */
internal class TodayRepositoryImpl(
    private val api: XiaomiApi,
    private val profileStore: ProfileRepository,
    private val cookies: AccountRepository,
) : TodayRepository {

    override suspend fun goldMiFeed(page: Int, pageSize: Int): TodayFeedPage = withContext(Dispatchers.Default) {
        api.goldMiFeed(page, pageSize, profileStore.load(), cookies.cookie())
    }

    override suspend fun todayArticle(rId: String): TodayArticle = withContext(Dispatchers.Default) {
        api.todayArticle(rId, profileStore.load(), cookies.cookie())
    }
}
