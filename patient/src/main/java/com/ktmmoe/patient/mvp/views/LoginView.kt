package com.ktmmoe.patient.mvp.views

import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 08, December, 2020
 **/
interface LoginView: BaseView {
    fun navigateToMain()
    fun navigateToRegister()
}