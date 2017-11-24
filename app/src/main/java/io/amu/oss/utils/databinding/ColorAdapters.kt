package io.amu.oss.utils.databinding

import android.databinding.BindingAdapter
import android.graphics.Color
import android.view.View
import android.widget.TextView
import io.amu.oss.utils.ColorUtils

@BindingAdapter("android:background")
fun setBackground(view: View, color: String) {
    val normalizedColor = ColorUtils.getNormalizedColor(color)
    view.setBackgroundColor(Color.parseColor(normalizedColor))
}

@BindingAdapter("notification_title")
fun setNotificationTitle(view: TextView, color: String) {
    val normalizedColor = ColorUtils.getNormalizedColor(color)
    setBackground(view, normalizedColor)
    view.setTextColor(Color.parseColor(ColorUtils.getTitleColor(normalizedColor)))
}