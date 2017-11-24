package io.amu.oss.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    private val isoFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    private val isoCompiledFormat = SimpleDateFormat(isoFormat, Locale.getDefault())

    fun toDate(isoDateString: String): Date = isoCompiledFormat.parse(isoDateString)

    fun toFormattedString(isoDateString: String, format: String = "MMMM d YYYY, h:mm:ss a"): String =
            SimpleDateFormat(format, Locale.getDefault()).format(toDate(isoDateString))

}
