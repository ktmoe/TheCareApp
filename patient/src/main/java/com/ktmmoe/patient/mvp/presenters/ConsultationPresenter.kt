package com.ktmmoe.patient.mvp.presenters

import com.ktmmoe.patient.mvp.views.ConsultationView
import com.ktmmoe.shared.mvp.presenters.BasePresenter
import com.ktmmoe.shared.viewpods.MessagingAreaViewPodDelegate
import com.ktmmoe.shared.viewpods.PatientPrescriptChatMessageViewPodDelegate

/**
 * Created by ktmmoe on 01, December, 2020
 **/
interface ConsultationPresenter: BasePresenter<ConsultationView>, MessagingAreaViewPodDelegate, PatientPrescriptChatMessageViewPodDelegate {
    fun onTapSeeMore()
}