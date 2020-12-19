package com.ktmmoe.patient.mvp.views

import com.ktmmoe.patient.viewholders.SpecialQAndAViewHolderDelegate
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.SpecialQuestion
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 05, December, 2020
 **/
interface CompleteSpecialQuestionsView:BaseView {
    fun getPassedPatientInfo(): Patient
    fun getPassedSpeciality(): Speciality
    fun showSpecialQuestions(specialQuestions: List<SpecialQuestion>)
    fun showConfirmPatientInfoDialog(patient: Patient)
    fun navigateBackToHome()
}