package com.ktmmoe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.doctor.mvp.presenters.PatientInfoPresenter
import com.ktmmoe.doctor.mvp.views.PatientInfoView
import com.ktmmoe.shared.data.models.DoctorModel
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.ConsultationRequest
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter
import java.util.*

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class PatientInfoPresenterImpl: AbstractBasePresenter<PatientInfoView>(), PatientInfoPresenter {
    private var consultationRequest: ConsultationRequest? = null
    private var mDoctor: Doctor? = null
    private val mDoctorModel: DoctorModel = DoctorModelImpl

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mDoctor = mDoctorModel.getPersistedDoctor().first()
        consultationRequest = mView.getPassedExtra()
        consultationRequest?.let { mView.showPatientInfo(it) }
    }

    override fun onTapStartConsultation() {
        consultationRequest?.let {request->
            val acceptedRequest = request.copy(
                    doctorId = mDoctor?.id ?: "",
                    doctor = mDoctor,
                    accepted = true
            )
            consultationRequest = acceptedRequest
            mDoctorModel.broadcastConsultationAccept(acceptedRequest, {
                addConsultation()
            }, {
                mView.showSnackBar(it)
            })
        }
    }

    private fun addConsultation() {
        consultationRequest?.let {r->
            mDoctorModel.addConsultation(getConsultation(r),{
               addPatientRecentDoctor()
            },{
                mView.showSnackBar(it)
            })
        }
    }

    private fun addPatientRecentDoctor(){
        var doctorList = mutableListOf<Doctor>()
        consultationRequest?.let {request->
            doctorList = request.patient.recentlyDoctors.toMutableList()
            doctorList.add(request.doctor?:Doctor())
            val patient = request.patient.copy(recentlyDoctors = doctorList)
            mDoctorModel.addPatientRecentDoctor(patient, {
                mView.navigateToConsultationScreen(request)
            } , {
                mView.showSnackBar(it)
            })
        }
    }

    private fun getConsultation(consultationRequest: ConsultationRequest) : Consultation =
            Consultation().apply {
                id = consultationRequest.id
                specialtyId = consultationRequest.specialtyId
                patient = consultationRequest.patient
                doctor = mDoctor ?: Doctor()
                patientId = consultationRequest.patientId
                doctorId = mDoctor?.id ?: ""
        }
}