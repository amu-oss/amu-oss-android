package io.amu.oss.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.amu.oss.OpenApplication
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

class MessagingService : FirebaseMessagingService(), AnkoLogger {

    @Inject lateinit var notificationService: NotificationService

    override fun onCreate() {
        super.onCreate()
        (application as OpenApplication).component.inject(this)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let {
            info("Notification Received : " + remoteMessage.from)
            notificationService.createNotification(it)
        }
    }

}