package io.amu.oss.ui.viewholder

import android.support.v7.widget.RecyclerView
import io.amu.oss.data.model.Notification
import io.amu.oss.databinding.ItemNotificationBinding

class NotificationHolder(private val binding: ItemNotificationBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(notification: Notification) {
        binding.notification = notification
        binding.executePendingBindings()
    }

}