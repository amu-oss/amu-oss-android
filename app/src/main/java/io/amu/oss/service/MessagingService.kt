package io.amu.oss.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.amu.oss.di.Components
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

class MessagingService : FirebaseMessagingService(), AnkoLogger {

    @Inject lateinit var notificationService: NotificationService

    init {
        Components.appComponent.inject(this)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let {
            info("Notification Received : " + remoteMessage.from)
            notificationService.createNotification(this, it)
        }
    }

}