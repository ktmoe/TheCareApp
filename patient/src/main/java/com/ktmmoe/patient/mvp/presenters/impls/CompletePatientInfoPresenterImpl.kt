package com.ktmmoe.patient.mvp.presenters.impls

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.CompletePatientInfoPresenter
import com.ktmmoe.patient.mvp.views.CompletePatientInfoView
import com.ktmmoe.shared.data.models.PatientModel
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 10, December, 2020
 **/
class CompletePatientInfoPresenterImpl: CompletePatientInfoPresenter, AbstractBasePresenter<CompletePatientInfoView>() {
    private var mPatient: Patient = Patient()
    private var mSpeciality : Speciality = Speciality()
    private val mPatientModel: PatientModel = PatientModelImpl


    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mPatient = mView.getPassedPatient()
        mSpeciality = mView.getPassedSpeciality()

        getSpecialQuestionsById()
    }

    private fun getSpecialQuestionsById() {
        mPatientModel.getSpecialQuestionsBySpeciality(mSpeciality.id, {
            mSpeciality = mSpeciality.copy(specialQuestions = it)
            onTapStep1()
        }, {
            mView.showSnackBar(it)
        })
    }

    override fun onTapStep1() {
        if (mPatient.dob.isEmpty()) mView.showCompleteOneTimeQuestionsFragment(mPatient, mSpeciality)
        else mView.showCompleteGeneralQuestionsFragment(mPatient, mSpeciality)
    }

    override fun onTapStep2() {
        mView.showSpecialQuestionsFragment(mPatient, mSpeciality)
    }
}