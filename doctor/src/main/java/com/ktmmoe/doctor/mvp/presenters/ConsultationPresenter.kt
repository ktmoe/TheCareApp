package com.ktmmoe.doctor.mvp.presenters

import com.ktmmoe.doctor.mvp.views.ConsultationView
import com.ktmmoe.shared.data.vos.SpecialQuestion
import com.ktmmoe.shared.mvp.presenters.BasePresenter
import com.ktmmoe.shared.viewpods.EasyDoctorActionsDelegate
import com.ktmmoe.shared.viewpods.MessagingAreaViewPodDelegate

/**
 * Created by ktmmoe on 01, December, 2020
 **/
interface ConsultationPresenter: BasePresenter<ConsultationView>, MessagingAreaViewPodDelegate, EasyDoctorActionsDelegate {
    fun onSpecialQuestionActivityResultOk(question: String)
    fun onPrescriptActivityResultOk()
    fun onTapSeeMore()
}