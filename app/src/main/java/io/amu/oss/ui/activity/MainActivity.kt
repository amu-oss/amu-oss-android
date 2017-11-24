package io.amu.oss.ui.activity

import android.os.Bundle
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.firebase.ui.common.ChangeEventType
import io.amu.oss.OpenApplication
import io.amu.oss.R
import io.amu.oss.arch.presenter.MainPresenter
import io.amu.oss.arch.view.MainView
import io.amu.oss.ui.adapter.NotificationBindingHelper
import io.amu.oss.utils.RecyclerUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView, AnkoLogger {

    @Inject lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as OpenApplication).component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainPresenter.setView(this)

        setupRecyclerView()
        fab.setOnClickListener {
            mainPresenter.controller.clickSubscribe()
        }
        fab.setOnLongClickListener {
            mainPresenter.controller.longClickSubscribe()
            true
        }
    }

    fun setupRecyclerView() {
        RecyclerUtils.setupReverseLayout(recyclerView)
        recyclerView.adapter = NotificationBindingHelper.getNotificationAdapter(this, this::handleDataChange)
        RecyclerUtils.attachFabHideBehavior(fab, recyclerView)
    }

    private fun handleDataChange(newPosition: Int, oldPosition: Int, type: ChangeEventType?) {
        type?.let {
            when (it) {
                ChangeEventType.ADDED -> {
                    hideProgressBar()
                    recyclerView.smoothScrollToPosition(newPosition)
                }
                else -> { /* Do Nothing */
                }
            }
        }
    }

    private fun hideProgressBar() {
        if (progressBar.visibility == View.VISIBLE)
            progressBar.visibility = View.GONE
    }

    override fun subscribed(subscribed: Boolean) {
        if (subscribed) subscribed() else unsubscribed()
    }

    override fun showToast(stringId: Int) {
        toast(stringId)
    }

    private fun subscribed() {
        fab.setImageDrawable(VectorDrawableCompat.create(resources, R.drawable.ic_close, theme))
    }

    private fun unsubscribed() {
        fab.setImageDrawable(VectorDrawableCompat.create(resources, R.drawable.ic_check, theme))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // TODO: Disabled menu for Future
        // menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
