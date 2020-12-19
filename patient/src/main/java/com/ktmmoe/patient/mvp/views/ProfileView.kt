package com.ktmmoe.patient.mvp.views

import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 13, December, 2020
 **/
interface ProfileView: BaseView {
    fun showPatientInfo(patient: Patient)
    fun showNoPatientInfoDialog()
    fun navigateToEditProfileScreen(patient:Patient)
    fun navigateToLoginScreen()
}