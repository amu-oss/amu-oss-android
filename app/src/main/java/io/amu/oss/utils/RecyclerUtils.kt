package io.amu.oss.utils

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.AnkoLogger

object RecyclerUtils : AnkoLogger {

    fun reverseLayoutManager(context: Context): LinearLayoutManager {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true

        return layoutManager
    }

    fun setupReverseLayout(recyclerView: RecyclerView) {
        recyclerView.layoutManager = reverseLayoutManager(recyclerView.context)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

}