package io.amu.oss.ui

import android.os.Bundle
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.amu.oss.OpenApplication
import io.amu.oss.R
import io.amu.oss.ui.presenter.MainPresenter
import io.amu.oss.ui.view.MainView

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

        fab.setOnClickListener {
            mainPresenter.controller.clickSubscribe()
        }
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun subscribed(subscribed: Boolean) {
        if (subscribed) subscribed() else unsubscribed()
    }

    private fun subscribed() {
        fab.setImageDrawable(VectorDrawableCompat.create(resources, R.drawable.ic_close, theme))
        subscribeStatus.text = "Unsubscribe"
        toast("Subscribed")
    }

    private fun unsubscribed() {
        fab.setImageDrawable(VectorDrawableCompat.create(resources, R.drawable.ic_check, theme))
        subscribeStatus.text = "Subscribe"
        toast("Unsubscribed")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Disabled menu for Future
        // menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
