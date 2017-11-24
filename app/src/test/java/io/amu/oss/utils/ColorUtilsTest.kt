package io.amu.oss.utils

import org.junit.Test
import org.junit.Assert.*

class ColorUtilsTest {

    @Test
    fun testLuminosityDark() {
        assertEquals(0.20, ColorUtils.getLuminosity("#333333"), 0.01)
    }

    @Test
    fun testLuminosityLight() {
        assertEquals(1.0, ColorUtils.getLuminosity("#ffffff"), 0.01)
    }

    @Test
    fun testLuminosityMedium() {
        assertEquals(0.51, ColorUtils.getLuminosity("#33ae80"), 0.01)
    }

    @Test
    fun testTitleColorLight() {
        assertEquals("#333333", ColorUtils.getTitleColor("#26C6DA"))
    }

    @Test
    fun testTitleColorDark() {
        assertEquals("#ffffff", ColorUtils.getTitleColor("#303F9F"))
    }

}