package com.ktmmoe.doctor.mvp.presenters

import com.ktmmoe.doctor.mvp.views.MainView
import com.ktmmoe.doctor.viewholders.ConsultationRecordItemDelegate
import com.ktmmoe.doctor.viewholders.NewConsultationRequestViewHolderDelegate
import com.ktmmoe.doctor.viewholders.OldConsultationRequestViewHolderDelegate
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 29, November, 2020
 **/
interface MainPresenter: BasePresenter<MainView>, NewConsultationRequestViewHolderDelegate, OldConsultationRequestViewHolderDelegate, ConsultationRecordItemDelegate {
    fun onTapProfile()
}