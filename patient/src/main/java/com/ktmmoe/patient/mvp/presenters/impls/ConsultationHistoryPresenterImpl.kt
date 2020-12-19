package com.ktmmoe.patient.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.ConsultationHistoryPresenter
import com.ktmmoe.patient.mvp.views.ConsultationHistoryView
import com.ktmmoe.shared.data.models.PatientModel
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 13, December, 2020
 **/
class ConsultationHistoryPresenterImpl: ConsultationHistoryPresenter, AbstractBasePresenter<ConsultationHistoryView>() {
    private val mPatientModel: PatientModel = PatientModelImpl


    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        val mPatient = mPatientModel.getPersistedPatient().first()
        mPatientModel.observeConsultationsByPatientId(mPatient.id, {
            if (it.isNotEmpty()) {
                mView.showChatHistories(it)
                mView.hideEmptyView()
            } else mView.showEmptyView()
        }, {
            mView.showSnackBar(it)
        })
    }

    override fun onTapMedicalRecord(consultation: Consultation) {
        mView.showPatientMedicalRecord(consultation.patient)
    }

    override fun onTapPrescriptions(consultation: Consultation) {
        mPatientModel.getPrescriptionsByConsultationId(consultation.id, {
            mView.showPrescriptionDialog(consultation, it)
        } , {
            mView.showSnackBar(it)
        })
    }

    override fun onTapRecord(consultation: Consultation) {
        mView.navigateToConsultationScreen(consultation)
    }
}