package com.app.market.domain.model.profile

import kotlin.test.Test
import kotlin.test.assertEquals

class SamsungRequestContextTest {
    @Test
    fun chinaAndGlobalContextsMatchProtocolRoutingInputs() {
        assertEquals(
            SamsungRequestContext("CHN", "zh_CN", "460", "00", "CHC"),
            SamsungStoreRegion.CHINA.requestContext(),
        )
        assertEquals(
            SamsungRequestContext("USA", "en_US", "310", "260", "XAA"),
            SamsungStoreRegion.GLOBAL.requestContext(),
        )
    }
}
