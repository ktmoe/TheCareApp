package com.ktmmoe.doctor.mvp.presenters

import com.ktmmoe.doctor.mvp.views.RegisterView
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 08, December, 2020
 **/
interface RegisterPresenter : BasePresenter<RegisterView>{
    fun onTapRegister(mail: String, password: String, userName: String)
    fun onTapBack()
    fun onTapLogin()
}