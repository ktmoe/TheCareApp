package com.ktmmoe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.doctor.mvp.presenters.MainPresenter
import com.ktmmoe.doctor.mvp.views.MainView
import com.ktmmoe.shared.data.models.DoctorModel
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 29, November, 2020
 **/
class MainPresenterImpl: MainPresenter, AbstractBasePresenter<MainView>() {
    private var mDoctor: Doctor = Doctor()
    private val mDoctorModel: DoctorModel = DoctorModelImpl

    override fun onTapProfile() {
        mView.navigateToProfileScreen(mDoctor)
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mDoctor = mDoctorModel.getPersistedDoctor().first()
        mView.bindDoctorInfo(mDoctor)
        mDoctorModel.observeDoctorById(mDoctor.id, {
            mDoctor = it
            mView.bindDoctorInfo(mDoctor)
        }, {
            mView.showSnackBar(it)
        })
        getConsultationRequests()
        observeConsultationsByDoctorId()
    }

    override fun onTapSkipNewRequest() {
        mView.hideNewConsultationRequest()
    }

    override fun onTapAcceptNewRequest(consultationRequest: ConsultationRequest) {
        mView.navigateToPatientInfo(consultationRequest)
    }

    override fun onTapLaterOldRequest() {
        mView.showSnackBar("Will Contact Later.")
    }

    override fun onTapScheduleOldRequest() {
        mView.showSelectTimeDialog()
    }

    override fun onTapAcceptOldRequest(consultationRequest: ConsultationRequest) {
        mView.navigateToPatientInfo(consultationRequest)
    }

    override fun onTapSendMessage(consultationRecord: Consultation) {
        mView.navigateToConsultationScreen(consultationRecord)
    }

    override fun onTapMedicalHistory(consultationRecord: Consultation) {
        mView.showMedicalHistoryDialog(consultationRecord)
    }

    override fun onTapPrescription(consultationRecord: Consultation) {
        mDoctorModel.getPrescriptionsByConsultationId(consultationRecord.id, {
            mView.showPrescriptionDialog(consultationRecord, it)
        }, { mView.showSnackBar(it) })
    }

    override fun onTapRemark(consultationRecord: Consultation) {
       mDoctorModel.getConsultationRemark(consultationRecord.id, {
           if (it.isNullOrEmpty()) mView.showSnackBar("No remark.")
           else mView.navigateToMedicalRecordScreen(consultationRecord, it)
       }, {
           mView.showSnackBar(it)
       })
    }

    private fun getConsultationRequests () {
        mDoctorModel.observeConsultationRequestsBySpecialtyId(mDoctor.specialtyId , {result->
            mView.showConsultationRequests(result.filter { !it.accepted })
        }, {error->
            mView.showSnackBar(error)
        })
    }

    private fun observeConsultationsByDoctorId() {
        mDoctorModel.observeConsultationsByDoctorId(mDoctor.id, { consultations ->
            if (consultations.isEmpty()) mView.showEmptyScheduleRecord()
            else {
                mView.hideEmptyScheduleRecord()
                mView.showConsultationRecord(consultations)
            }
        }, { failure ->
            mView.showSnackBar(failure)
        })
    }

}