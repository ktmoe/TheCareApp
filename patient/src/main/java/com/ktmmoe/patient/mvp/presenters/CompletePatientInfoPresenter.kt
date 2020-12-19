package com.ktmmoe.patient.mvp.presenters

import com.ktmmoe.patient.mvp.views.CompletePatientInfoView
import com.ktmmoe.patient.viewpods.StateProgressViewPod
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 10, December, 2020
 **/
interface CompletePatientInfoPresenter: BasePresenter<CompletePatientInfoView>, StateProgressViewPod.Delegate {
}