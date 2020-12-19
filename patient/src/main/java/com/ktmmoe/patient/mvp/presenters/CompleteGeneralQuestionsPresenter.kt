package com.ktmmoe.patient.mvp.presenters

import com.ktmmoe.patient.mvp.views.CompleteGeneralQuestionsView
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 05, December, 2020
 **/
interface CompleteGeneralQuestionsPresenter: BasePresenter<CompleteGeneralQuestionsView> {
    fun onTapNext(weight: String, bloodPressure: String, onValid: () -> Unit)
}