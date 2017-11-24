package io.amu.oss.arch.presenter

import io.amu.oss.arch.controller.MainController
import io.amu.oss.arch.view.MainView
import io.amu.oss.service.TopicService
import javax.inject.Inject

class MainPresenter @Inject constructor(val topicService: TopicService) {

    private val TOPIC = "all"
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

    val controller = object : MainController {

        override fun clickSubscribe() {
            if (subscribed) unSubscribeFromTopic() else subscribeToTopic()
        }

    }

}
