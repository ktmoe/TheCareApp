package com.ktmmoe.patient.mvp.views

import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 05, December, 2020
 **/
interface CompleteGeneralQuestionsView: BaseView {
    fun getPassedPatientInfo(): Patient
    fun getPassedSpeciality(): Speciality
    fun showSpecialQuestions(patient: Patient, speciality: Speciality)
    fun showPatientInfo(patient: Patient)
}