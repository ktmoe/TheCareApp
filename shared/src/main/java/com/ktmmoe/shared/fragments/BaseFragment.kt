package com.ktmmoe.shared.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter
import com.ktmmoe.shared.mvp.views.BaseView
import com.ktmmoe.shared.utils.showSnackBar

/**
 * Created by ktmmoe on 29, November, 2020
 **/
abstract class BaseFragment: Fragment(), BaseView {
    inline fun <reified T : AbstractBasePresenter<W>, reified W: BaseView> getPresenter(): T {
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W) presenter.initPresenter(this)
        return presenter
    }

    override fun showSnackBar(message: String) {
        requireView().showSnackBar(message)
    }
}