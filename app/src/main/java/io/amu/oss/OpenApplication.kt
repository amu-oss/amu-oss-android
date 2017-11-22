package io.amu.oss

import android.app.Application
import io.amu.oss.di.component.AppComponent
import io.amu.oss.di.component.DaggerAppComponent
import io.amu.oss.di.module.AppModule

class OpenApplication: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }


}
