package com.ktmmoe.patient.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.CompleteOneTimeQuestionsPresenter
import com.ktmmoe.patient.mvp.views.CompleteOneTimeQuestionsView
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 05, December, 2020
 **/
class CompleteOneTimeQuestionsPresenterImpl: CompleteOneTimeQuestionsPresenter, AbstractBasePresenter<CompleteOneTimeQuestionsView>() {
    private var mPatient: Patient? = null
    private var mSpeciality: Speciality = Speciality()

    private var mDob: String = ""

    override fun onTapNext(mheight: String, bloodType: String, weight: String, mbloodPressure: String, mallergic: String, callback: () -> Unit) {
        if (validateInputs(mheight, bloodType, weight, mbloodPressure, mallergic)) {
            mPatient?.let {
                it.apply {
                    dob = mDob
                    height = mheight
                    this.bloodType = bloodType
                    this.weight = weight
                    bloodPressure = mbloodPressure
                    allergicPrescriptions = mallergic
                }
                callback()
                mView.showSpecialQuestions(it, mSpeciality)
            }
        }
    }

    private fun validateInputs(height: String, bloodType: String, weight: String, bloodPressure: String, allergic: String) =
        if (mDob.isNullOrEmpty() || height.isNullOrEmpty() || bloodType.isNullOrEmpty() || weight.isNullOrEmpty() || bloodPressure.isNullOrEmpty() || allergic.isNullOrEmpty()) {
            mView.showSnackBar("Please fill in all fields.")
            false
        } else true

    override fun onTapDOB() {
        mView.showDatePickerDialog { d, m, y->
            mDob = "$d-$m-$y"
            mView.showPickedDOB(d, m, y)
        }
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mPatient = mView.getPassedPatientInfo()
        mSpeciality = mView.getPassedSpeciality()
    }


}