package io.amu.oss.di.module

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import io.amu.oss.OpenApplication
import org.jetbrains.anko.defaultSharedPreferences

@Module
@Singleton
class AppModule (private val app: OpenApplication) {

    @Provides
    fun getApplication(): OpenApplication = app

    @Provides
    fun getContext(): Context = app

    @Provides
    fun getSharedPreferences(context: Context): SharedPreferences = context.defaultSharedPreferences

}
