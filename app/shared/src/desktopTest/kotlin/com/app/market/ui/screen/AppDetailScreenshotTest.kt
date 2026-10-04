package com.app.market.ui.screen

import com.app.market.domain.model.market.ScreenshotOrientation
import kotlin.test.Test
import kotlin.test.assertEquals

class AppDetailScreenshotTest {

    @Test
    fun screenshotRatioIsKnownBeforeImageLoading() {
        assertEquals(5f / 9f, screenshotAspectRatio(ScreenshotOrientation.PORTRAIT))
        assertEquals(16f / 9f, screenshotAspectRatio(ScreenshotOrientation.LANDSCAPE))
    }

}
