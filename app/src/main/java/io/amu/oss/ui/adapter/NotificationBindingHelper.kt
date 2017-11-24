package io.amu.oss.ui.adapter

import android.arch.lifecycle.LifecycleOwner
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.common.ChangeEventType
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import io.amu.oss.data.model.Notification
import io.amu.oss.databinding.ItemNotificationBinding
import io.amu.oss.ui.viewholder.NotificationHolder
import org.jetbrains.anko.AnkoLogger

object NotificationBindingHelper : AnkoLogger {

    private val query = FirebaseDatabase.getInstance().getReference("notifications")

    fun getNotificationAdapter(lifecycleOwner: LifecycleOwner? = null, consumer: (Int, Int, ChangeEventType?) -> Unit): RecyclerView.Adapter<NotificationHolder> {
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

            override fun onChildChanged(type: ChangeEventType?, snapshot: DataSnapshot?, newIndex: Int, oldIndex: Int) {
                super.onChildChanged(type, snapshot, newIndex, oldIndex)
                consumer(newIndex, oldIndex, type)
            }
        }
    }

}