package com.ktmmoe.shared.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.ktmmoe.shared.R
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter
import com.ktmmoe.shared.mvp.views.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    inline fun <reified T : AbstractBasePresenter<W>, reified W: BaseView> getPresenter(): T {
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W) presenter.initPresenter(this)
        return presenter
    }

    override fun showSnackBar(message: String) {
        val m = if (message.isNullOrEmpty()) resources.getString(R.string.wip_feature) else message
        Snackbar.make(window.decorView, m, Snackbar.LENGTH_LONG).show()
    }

    protected fun setupActionBar(toolbar: Toolbar, text: String = "", callback:(()->Unit)? = null) {
        setSupportActionBar(toolbar)
        if (text.isNotEmpty()) title = text
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener { callback?.invoke() ?: onBackPressed() }
    }

}