package com.ktmmoe.patient.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.*
import com.ktmmoe.patient.mvp.presenters.CategoriesPresenter
import com.ktmmoe.patient.mvp.views.CategoriesView
import com.ktmmoe.shared.data.models.PatientModel
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 29, November, 2020
 **/
class CategoriesPresenterImpl: CategoriesPresenter, AbstractBasePresenter<CategoriesView>() {

    private val mPatientModel: PatientModel = PatientModelImpl
    private var mPatient: Patient? = null

    override fun onTapRecentDoctor(data: Doctor) {
    }

    override fun onTapStartConsultation(consultation: Consultation) {
        mPatientModel.markConsultationStarted(consultation.copy(consultationStarted = true), {mView.gotoConsultationScreen(consultation.copy(consultationStarted = true))}, {mView.showSnackBar(it)})
    }

    override fun onTapConfirmConsultation(speciality: Speciality) {
        mPatient?.let {
            mView.navigateToCompletePatientInfo(speciality, it)
        }
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        getSpecialities()

        mPatient = mPatientModel.getPersistedPatient().first()
        mPatientModel.observePatientById(mPatient!!.id, {patient ->
            mPatient = patient
            if (patient.recentlyDoctors.isNullOrEmpty()) {
                mView.hideRecentConsultations()
            } else mView.showRecentConsultations(patient.recentlyDoctors)
        }, {} )

        mPatient?.let { observeConsultationsByPatientId(it.id) }

        mPatientModel.getPersistedSpecialities().observe(owner, {
            mView.showSpecialities(it)
        })
    }

    private fun observeConsultationsByPatientId(patientId: String) {
        mPatientModel.observeConsultationsByPatientId(patientId, {
            mView.showAcceptedConsultations(it.filter { it.consultedDate.isNullOrEmpty() })
        }, {
            mView.showSnackBar(it)
        })
    }

    override fun onTapSpeciality(data: Speciality) {
        mView.showConsultationConfirmDialog(data)
    }

    private fun getSpecialities() {
        mPatientModel.getSpecialitiesAndSaveToDatabase {
            mView.showSnackBar(it)
        }
    }
}