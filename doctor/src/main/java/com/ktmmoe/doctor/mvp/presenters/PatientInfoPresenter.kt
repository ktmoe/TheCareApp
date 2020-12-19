package com.ktmmoe.doctor.mvp.presenters

import com.ktmmoe.doctor.mvp.views.PatientInfoView
import com.ktmmoe.shared.data.vos.ConsultationRequest
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 30, November, 2020
 **/
interface PatientInfoPresenter: BasePresenter<PatientInfoView> {
    fun onTapStartConsultation()
}