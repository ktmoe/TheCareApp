package com.ktmmoe.patient.mvp.presenters.impls

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.CompleteSpecialQuestionsPresenter
import com.ktmmoe.patient.mvp.views.CompleteSpecialQuestionsView
import com.ktmmoe.shared.data.models.PatientModel
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ktmmoe on 05, December, 2020
 **/
class CompleteSpecialQuestionsPresenterImpl: CompleteSpecialQuestionsPresenter, AbstractBasePresenter<CompleteSpecialQuestionsView>() {
    private val mPatientModel: PatientModel = PatientModelImpl

    private var mPatient: Patient? = null
    private var mSpeciality: Speciality? = null
    private var mCaseSummaryList: MutableList<CaseSummary> = mutableListOf()

    override fun onTapConfirmPatientInfo() {
        mPatient = mPatient?.copy(caseSummary = mCaseSummaryList)
        mPatient?.let {
            mView.showConfirmPatientInfoDialog(it)
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onConfirmConsultationRequest() {
        val consultationRequest = ConsultationRequest(
                id = UUID.randomUUID().toString(),
                specialtyId = "${mSpeciality?.id}" ,
                patient = mPatient ?: Patient(),
                patientId = mPatient?.id ?: "",
                dateTime = SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(Date())
        )
        mPatientModel.broadcastConsultationRequest(consultationRequest, {
            updatePatient()
        }, {
            mView.showSnackBar(it)
        })
    }

    private fun updatePatient() {
        mPatient?.let {
            mPatientModel.addPatient(it, {
                mView.navigateBackToHome()
            }, {failure->
                mView.showSnackBar(failure)
            })
        }
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mPatient = mView.getPassedPatientInfo()
        mSpeciality = mView.getPassedSpeciality()

        mSpeciality?.let {
            mView.showSpecialQuestions(it.specialQuestions)
        }
    }

    override fun onAnswered(caseSummary: CaseSummary) {
        mCaseSummaryList.find { it.id == caseSummary.id }?.let {
            mCaseSummaryList.remove(it)
            if (caseSummary.answer.isNotEmpty()) mCaseSummaryList.add(it.copy(answer = caseSummary.answer))
        } ?: mCaseSummaryList.add(caseSummary)
    }
}