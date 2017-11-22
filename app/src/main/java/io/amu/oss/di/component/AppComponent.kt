package io.amu.oss.di.component

import dagger.Component
import io.amu.oss.service.MessagingService
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent {

    fun inject(service: MessagingService)

}