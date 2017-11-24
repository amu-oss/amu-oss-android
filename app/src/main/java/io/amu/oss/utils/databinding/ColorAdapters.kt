package io.amu.oss.utils.databinding

import android.databinding.BindingAdapter
import android.graphics.Color
import android.view.View
import android.widget.TextView
import io.amu.oss.utils.ColorUtils

@BindingAdapter("android:background")
fun setBackground(view: View, color: String) {
    view.setBackgroundColor(Color.parseColor(color))
}

@BindingAdapter("notification_title")
fun setNotificationTitle(view: TextView, color: String) {
    setBackground(view, color)
    view.setTextColor(Color.parseColor(ColorUtils.getTitleColor(color)))
}