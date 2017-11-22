package io.amu.oss.ui.presenter

import io.amu.oss.service.TopicService
import io.amu.oss.ui.controller.MainController
import io.amu.oss.ui.view.MainView
import javax.inject.Inject

class MainPresenter @Inject constructor(val topicService: TopicService) {

    private val TOPIC: String = "all"
    private var mainView: MainView? = null

    private var subscribed: Boolean = false

    init {
        setSubscribed(topicService.isSubscribed(TOPIC))
    }

    fun setView(mainView: MainView) {
        this.mainView = mainView
        setSubscribed(subscribed)
    }

    private fun setSubscribed(subscribed: Boolean) {
        this.subscribed = subscribed
        mainView?.subscribed(subscribed)
    }

    private fun subscribeToTopic() {
        topicService.subscribe(TOPIC)
        setSubscribed(true)
    }

    private fun unSubscribeFromTopic() {
        topicService.unsubscribe(TOPIC)
        setSubscribed(false)
    }

    val controller = object : MainController  {

        override fun clickSubscribe() {
            if (subscribed) unSubscribeFromTopic() else subscribeToTopic()
        }

    }

}
