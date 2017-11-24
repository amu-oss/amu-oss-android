package io.amu.oss.utils

import org.jetbrains.anko.AnkoLogger

object ColorUtils : AnkoLogger {

    data class RGB(val r: Int, val g: Int, val b: Int)

    fun getRGB(hexColor: String, rgb: RGB = RGB(0, 0, 0)) =
            when (hexColor.length) {
                7 -> getRGBFromLargeHex(hexColor)
                4 -> getRGBFromSmallHex(hexColor)
                else -> rgb
            }

    fun getLuminosity(hexColor: String): Double {
        val (r, g, b) = getRGB(hexColor)
        return (0.299 * r + 0.587 * g + 0.114 * b) / 255.0
    }

    fun getNormalizedColor(hexColor: String): String {
        if (hexColor.length == 7 || hexColor.length != 4)
            return hexColor

        val r = hexColor[1]
        val g = hexColor[2]
        val b = hexColor[3]

        val builder = StringBuilder(hexColor)
        builder.insert(1, r)
        builder.insert(3, g)
        builder.insert(5, b)

        return builder.toString()
    }

    fun getTitleColor(hexColor: String): String {
        if (getLuminosity(hexColor) > 0.4)
            return "#aa333333"
        return "#aaffffff"
    }

    private fun getRGBFromSmallHex(hexColor: String): RGB {
        val r = Integer.valueOf(hexColor.substring(1, 2), 16)
        val g = Integer.valueOf(hexColor.substring(2, 3), 16)
        val b = Integer.valueOf(hexColor.substring(3, 4), 16)

        return RGB(r, g, b)
    }

    private fun getRGBFromLargeHex(hexColor: String): RGB {
        val r = Integer.valueOf(hexColor.substring(1, 3), 16)
        val g = Integer.valueOf(hexColor.substring(3, 5), 16)
        val b = Integer.valueOf(hexColor.substring(5, 7), 16)

        return RGB(r, g, b)
    }

}