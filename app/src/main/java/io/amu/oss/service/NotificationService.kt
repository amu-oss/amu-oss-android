package io.amu.oss.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import com.google.firebase.messaging.RemoteMessage
import io.amu.oss.R
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationService @Inject constructor(val context: Context, val notificationManager: NotificationManager) : AnkoLogger {

    private val NOTIFICATION_ID = 3245
    private val NOTIFICATION_CHANNEL_ID = "ALL_OSS"

    private var initialized = false

    fun createNotification(notification: RemoteMessage.Notification) {
        if (!initialized && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
            initialized = true
        }

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setStyle(NotificationCompat.BigTextStyle())
                .setSmallIcon(R.drawable.ic_notification)
                .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setContentTitle(notification.title)
                .setContentText(notification.body)
                .setDefaults(Notification.DEFAULT_SOUND)

        notification.color?.let {
            builder.color = Color.parseColor(it)
        }

        sendNotification(builder.build())
    }

    fun sendNotification(notification: Notification, id: Int = NOTIFICATION_ID) {
        notificationManager.notify(id, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannel(
            id: String = NOTIFICATION_CHANNEL_ID,
            name: String = context.getString(R.string.all_channel_name),
            description: String =context.getString(R.string.all_channel_description)
    ) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)
        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.WHITE
        channel.enableVibration(true)
        notificationManager.createNotificationChannel(channel)
    }

}
