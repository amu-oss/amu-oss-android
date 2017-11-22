package io.amu.oss.di.module

import android.app.NotificationManager
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module(includes = arrayOf(AppModule::class))
class NotificationModule {

    @Provides
    fun getNotificationManager(context: Context) : NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

}
