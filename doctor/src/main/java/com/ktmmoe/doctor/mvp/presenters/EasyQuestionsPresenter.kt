package com.ktmmoe.doctor.mvp.presenters

import com.ktmmoe.doctor.mvp.views.EasyQuestionsView
import com.ktmmoe.doctor.viewholders.EasyQuestionViewHolderDelegate
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 03, December, 2020
 **/
interface EasyQuestionsPresenter: BasePresenter<EasyQuestionsView>, EasyQuestionViewHolderDelegate {
}