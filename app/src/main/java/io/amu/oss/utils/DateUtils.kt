package io.amu.oss.utils

import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object DateUtils {

    fun toDate(isoDateString: String): ZonedDateTime = ZonedDateTime.parse(isoDateString)

    fun toFormattedString(isoDateString: String, format: String = "MMMM d YYYY, h:mm:ss a"): String =
            toDate(isoDateString)
                    .withZoneSameInstant(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern(format))

}
