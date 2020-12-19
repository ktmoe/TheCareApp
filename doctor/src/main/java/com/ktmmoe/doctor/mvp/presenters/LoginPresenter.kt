package com.ktmmoe.doctor.mvp.presenters

import com.ktmmoe.doctor.mvp.views.LoginView
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 08, December, 2020
 **/
interface LoginPresenter: BasePresenter<LoginView> {
    fun onTapLogin(mail: String, password: String)
    fun onTapRegister()
    fun onTapForgetPassword()
    fun onTapFacebookSignIn()
}