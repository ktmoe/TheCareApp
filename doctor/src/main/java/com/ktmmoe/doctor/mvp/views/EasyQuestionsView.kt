package com.ktmmoe.doctor.mvp.views

import com.ktmmoe.shared.data.vos.SpecialQuestion
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 03, December, 2020
 **/
interface EasyQuestionsView: BaseView {
    fun showPredefinedQuestions(questions: List<String>)
    fun finishAndSetResult(easyQuestion: String?)
}
