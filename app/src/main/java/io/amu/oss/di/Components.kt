package io.amu.oss.di

import io.amu.oss.di.component.AppComponent
import io.amu.oss.di.component.DaggerAppComponent

object Components {

    val appComponent: AppComponent = DaggerAppComponent.builder().build()

}