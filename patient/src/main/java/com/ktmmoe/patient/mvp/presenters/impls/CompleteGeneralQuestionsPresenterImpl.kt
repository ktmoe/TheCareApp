package com.ktmmoe.patient.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.CompleteGeneralQuestionsPresenter
import com.ktmmoe.patient.mvp.views.CompleteGeneralQuestionsView
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 05, December, 2020
 **/
class CompleteGeneralQuestionsPresenterImpl: CompleteGeneralQuestionsPresenter, AbstractBasePresenter<CompleteGeneralQuestionsView>() {
    private var mPatient: Patient? = null
    private var mSpeciality: Speciality? = null

    override fun onTapNext(weight: String, bloodPressure: String, onValid: () -> Unit) {
        if (weight.isNullOrEmpty() || bloodPressure.isNullOrEmpty()) {
            mView.showSnackBar("Fill in all fields.")
        }
        else {
            if (mPatient != null && mSpeciality != null) {
                mPatient = mPatient?.copy(weight = weight, bloodPressure = bloodPressure)
                onValid.invoke()
                mView.showSpecialQuestions(mPatient!!, mSpeciality!!)
            }
        }
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mPatient = mView.getPassedPatientInfo()
        mSpeciality = mView.getPassedSpeciality()

        mPatient?.let { mView.showPatientInfo(patient = it) }
    }
}