package com.ktmmoe.patient.mvp.presenters

import com.ktmmoe.patient.mvp.views.CompleteSpecialQuestionsView
import com.ktmmoe.patient.viewholders.SpecialQAndAViewHolderDelegate
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 05, December, 2020
 **/
interface CompleteSpecialQuestionsPresenter: BasePresenter<CompleteSpecialQuestionsView>, SpecialQAndAViewHolderDelegate {
    fun onTapConfirmPatientInfo()
    fun onConfirmConsultationRequest()
}