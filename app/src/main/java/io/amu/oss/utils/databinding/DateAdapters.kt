package io.amu.oss.utils.databinding

import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import io.amu.oss.utils.DateUtils

@BindingAdapter("date")
fun setDate(textView: TextView, isoDateString: String?) {
    if (isoDateString != null) {
        textView.text = DateUtils.toFormattedString(isoDateString)
        textView.visibility = View.VISIBLE
    } else {
        textView.visibility = View.GONE
    }
}
