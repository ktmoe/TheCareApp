package com.ktmmoe.patient.mvp.presenters

import com.ktmmoe.patient.mvp.views.CompleteOneTimeQuestionsView
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 05, December, 2020
 **/
interface CompleteOneTimeQuestionsPresenter: BasePresenter<CompleteOneTimeQuestionsView> {
    fun onTapNext(height: String, bloodType: String, weight: String, bloodPressure: String, allergic: String, callback: ()->Unit)
    fun onTapDOB()
}