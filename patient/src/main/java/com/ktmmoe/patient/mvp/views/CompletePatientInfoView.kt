package com.ktmmoe.patient.mvp.views

import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 10, December, 2020
 **/
interface CompletePatientInfoView: BaseView {
    fun getPassedPatient(): Patient
    fun getPassedSpeciality(): Speciality
    fun showCompleteGeneralQuestionsFragment(patient: Patient, speciality: Speciality)
    fun showCompleteOneTimeQuestionsFragment(patient: Patient, speciality: Speciality)
    fun showSpecialQuestionsFragment(patient: Patient, speciality: Speciality)
}