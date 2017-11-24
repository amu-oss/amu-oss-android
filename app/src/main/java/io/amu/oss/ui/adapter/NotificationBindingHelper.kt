package io.amu.oss.ui.adapter

import android.arch.lifecycle.LifecycleOwner
import android.support.v7.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import io.amu.oss.ui.viewholder.NotificationHolder
import com.firebase.ui.database.FirebaseRecyclerOptions
import io.amu.oss.data.model.Notification
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import io.amu.oss.databinding.ItemNotificationBinding
import org.jetbrains.anko.AnkoLogger

object NotificationBindingHelper: AnkoLogger {

    private val query = FirebaseDatabase.getInstance().getReference("notifications")

    fun getNotificationAdapter(lifecycleOwner: LifecycleOwner? = null): RecyclerView.Adapter<NotificationHolder> {
        val options = FirebaseRecyclerOptions.Builder<Notification>()
                .setQuery(query, Notification::class.java)
                .setLifecycleOwner(lifecycleOwner)
                .build()

        return object : FirebaseRecyclerAdapter<Notification, NotificationHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
                return NotificationHolder(ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }

            override fun onBindViewHolder(holder: NotificationHolder, position: Int, model: Notification) {
                holder.bind(model)
            }
        }
    }

}