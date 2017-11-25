package io.amu.oss.utils

import org.junit.Test
import org.junit.Assert.*

class DateUtilsTest {

    @Test
    fun testDateFormatGlobal() {
        assertEquals("November 23 2017, 6:28:43 PM", DateUtils.toFormattedString("2017-11-23T12:58:43.067Z"))
        assertEquals("November 25 2017, 2:15:42 PM", DateUtils.toFormattedString("2017-11-25T08:45:42.702Z"))
    }

}
