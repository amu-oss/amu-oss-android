package io.amu.oss

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import com.jakewharton.threetenabp.AndroidThreeTen
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
        AndroidThreeTen.init(this)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }


}
