package com.ktmmoe.doctor.mvp.views

import com.ktmmoe.shared.data.vos.ConsultationRequest
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 30, November, 2020
 **/
interface PatientInfoView: BaseView {
    fun getPassedExtra(): ConsultationRequest?
    fun showPatientInfo(consultationRequest: ConsultationRequest)
    fun navigateToConsultationScreen(consultationRequest: ConsultationRequest)
}