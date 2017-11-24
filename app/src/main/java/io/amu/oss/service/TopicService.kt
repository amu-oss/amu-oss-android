package io.amu.oss.service

import android.content.SharedPreferences
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class TopicService @Inject constructor(val sharedPreferences: SharedPreferences) {

    private val preferenceEditor: SharedPreferences.Editor = sharedPreferences.edit()

    fun isFirstTime(): Boolean {
        val key = "first_time_topic"
        val firstTime = sharedPreferences.getBoolean(key, true)
        if (firstTime)
            preferenceEditor.putBoolean(key, false).apply()

        return firstTime
    }

    fun isSubscribed(topic: String): Boolean {
        return sharedPreferences.getBoolean(getKey(topic), false)
    }

    fun subscribe(topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
        preferenceEditor.putBoolean(getKey(topic), true).apply()
    }

    fun unsubscribe(topic: String) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
        preferenceEditor.putBoolean(getKey(topic), false).apply()
    }

    private fun getKey(topic: String) = "topic" + topic

}
