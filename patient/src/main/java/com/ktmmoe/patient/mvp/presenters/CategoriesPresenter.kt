package com.ktmmoe.patient.mvp.presenters

import com.ktmmoe.patient.mvp.views.CategoriesView
import com.ktmmoe.patient.viewholders.AcceptedConsultationViewHolderDelegate
import com.ktmmoe.patient.viewholders.SpecialitiesViewHolderDelegate
import com.ktmmoe.patient.viewholders.RecentDoctorDelegate
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 29, November, 2020
 **/
interface CategoriesPresenter: BasePresenter<CategoriesView>, SpecialitiesViewHolderDelegate, RecentDoctorDelegate, AcceptedConsultationViewHolderDelegate {
    fun onTapConfirmConsultation(speciality: Speciality)
}