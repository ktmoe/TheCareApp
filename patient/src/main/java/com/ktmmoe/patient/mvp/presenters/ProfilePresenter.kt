package com.ktmmoe.patient.mvp.presenters

import com.ktmmoe.patient.mvp.views.ProfileView
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 13, December, 2020
 **/
interface ProfilePresenter: BasePresenter<ProfileView> {
    fun onTapEditProfile()
    fun onTapLogout()
    fun onTapChangePassword()
}