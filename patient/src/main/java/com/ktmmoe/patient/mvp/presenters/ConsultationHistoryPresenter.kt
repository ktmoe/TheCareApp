package com.ktmmoe.patient.mvp.presenters

import com.ktmmoe.patient.mvp.views.ConsultationHistoryView
import com.ktmmoe.patient.viewholders.ConsultationHistoryViewHolderDelegate
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 13, December, 2020
 **/
interface ConsultationHistoryPresenter: BasePresenter<ConsultationHistoryView>, ConsultationHistoryViewHolderDelegate {
}