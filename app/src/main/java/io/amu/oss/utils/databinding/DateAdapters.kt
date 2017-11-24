package io.amu.oss.utils.databinding

import android.databinding.BindingAdapter
import android.widget.TextView
import io.amu.oss.utils.DateUtils

@BindingAdapter("date")
fun setDate(textView: TextView, isoDateString: String) {
    textView.text = DateUtils.toFormattedString(isoDateString)
}
