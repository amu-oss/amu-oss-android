package io.amu.oss.di.module

import android.content.Context
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.amu.oss.OpenApplication

@Module
@Singleton
class AppModule (private val app: OpenApplication) {

    @Provides
    fun getApplication(): OpenApplication = app

    @Provides
    fun getContext(): Context = app

}
