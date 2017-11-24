package io.amu.oss.arch.presenter

import io.amu.oss.R
import io.amu.oss.arch.controller.MainController
import io.amu.oss.arch.view.MainView
import io.amu.oss.service.TopicService
import javax.inject.Inject

class MainPresenter @Inject constructor(val topicService: TopicService) {

    private val TOPIC = "all"
    private var mainView: MainView? = null

    private var subscribed: Boolean = false

    init {
        setSubscribed(topicService.isSubscribed(TOPIC), false)
        if (topicService.isFirstTime())
            subscribeToTopic()
    }

    fun setView(mainView: MainView) {
        this.mainView = mainView
        setSubscribed(subscribed, false)
    }

    private fun setSubscribed(subscribed: Boolean, showToast: Boolean = true) {
        this.subscribed = subscribed
        mainView?.subscribed(subscribed)
        if (showToast)
            mainView?.showToast(if (subscribed) R.string.subscribed else R.string.unsubscribed)
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

        override fun longClickSubscribe() {
            mainView?.showToast(if (subscribed) R.string.unsubscribe else R.string.subscribe)
        }

    }

}
