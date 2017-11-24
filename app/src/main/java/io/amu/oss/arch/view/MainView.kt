package io.amu.oss.arch.view

import android.support.annotation.StringRes

interface MainView {

    fun subscribed(subscribed: Boolean)

    fun showToast(@StringRes stringId: Int)

}