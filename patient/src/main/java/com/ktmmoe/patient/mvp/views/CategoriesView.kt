package com.ktmmoe.patient.mvp.views

import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 29, November, 2020
 **/
interface CategoriesView: BaseView {
    fun showAcceptedConsultations(data: List<Consultation>)
    fun showSpecialities(data: List<Speciality>)
    fun showRecentConsultations(data: List<Doctor>)
    fun hideRecentConsultations()
    fun showConsultationConfirmDialog(speciality: Speciality)
    fun navigateToCompletePatientInfo(speciality: Speciality, patient: Patient)
    fun gotoConsultationScreen(consultation: Consultation)
}