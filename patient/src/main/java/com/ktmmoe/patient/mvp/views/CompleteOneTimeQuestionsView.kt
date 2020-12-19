package com.ktmmoe.patient.mvp.views

import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 04, December, 2020
 **/
interface CompleteOneTimeQuestionsView: BaseView {
    fun getPassedPatientInfo(): Patient
    fun getPassedSpeciality(): Speciality
    fun showSpecialQuestions(patient: Patient, speciality: Speciality)
    fun showDatePickerDialog(onPicked: (Int, Int, Int) -> Unit)
    fun showPickedDOB(day: Int, month: Int, year: Int)
}