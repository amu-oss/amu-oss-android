package io.amu.oss.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import io.amu.oss.R
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationService @Inject constructor() : AnkoLogger {

    private val NOTIFICATION_ID: Int = 3245
    private val NOTIFICATION_CHANNEL_ID: String = "ALL_OSS"
    private val NOTIFICATION_CHANNEL_NAME: String = "All Notifications"
    private val NOTIFICATION_CHANNEL_DESCRIPTION = "All notifications from AMU OSS"

    private var initialized: Boolean = false

    fun createNotification(context: Context, notification: RemoteMessage.Notification) {
        if (!initialized && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(context)
            initialized = true
        }

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(notification.title)
                .setContentText(notification.body)
                .setDefaults(Notification.DEFAULT_SOUND)

        notification.color?.let {
            builder.color = Color.parseColor(it)
        }

        sendNotification(context, builder)
    }

    fun sendNotification(context: Context, builder: NotificationCompat.Builder, id: Int = NOTIFICATION_ID) {
        val notifyManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notifyManager.notify(id, builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createChannel(
            context: Context,
            id: String = NOTIFICATION_CHANNEL_ID,
            name: String = NOTIFICATION_CHANNEL_NAME,
            description: String = NOTIFICATION_CHANNEL_DESCRIPTION
    ) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)
        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        notificationManager.createNotificationChannel(channel)
    }

}
