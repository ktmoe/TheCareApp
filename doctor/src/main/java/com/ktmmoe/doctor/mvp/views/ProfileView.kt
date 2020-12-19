package com.ktmmoe.doctor.mvp.views

import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 02, December, 2020
 **/
interface ProfileView: BaseView {
    fun getPassedDoctor(): Doctor
    fun showDoctorProfile(doctor: Doctor)
    fun navigateToEditProfile()
    fun navigateToLoginScreen()
}