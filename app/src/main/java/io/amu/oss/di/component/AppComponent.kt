package io.amu.oss.di.component

import dagger.Component
import io.amu.oss.OpenApplication
import io.amu.oss.di.module.NotificationModule
import io.amu.oss.service.MessagingService
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NotificationModule::class))
interface AppComponent {

    fun inject(app: OpenApplication)

    fun inject(service: MessagingService)

}