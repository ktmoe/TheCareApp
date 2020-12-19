package com.ktmmoe.doctor.mvp.presenters

import com.ktmmoe.doctor.mvp.views.ProfileView
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 02, December, 2020
 **/
interface ProfilePresenter: BasePresenter<ProfileView> {
    fun onTapEdit()
    fun onTapForgetPassword()
    fun onTapChangePassword()
    fun onTapLogout()
}