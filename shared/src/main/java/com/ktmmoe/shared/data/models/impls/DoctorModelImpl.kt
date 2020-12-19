package com.ktmmoe.shared.data.models.impls

import android.annotation.SuppressLint
import android.graphics.Bitmap
import com.ktmmoe.shared.data.models.BaseModel
import com.ktmmoe.shared.data.models.DoctorModel
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.network.CloudFirebaseApiImpl
import com.ktmmoe.shared.network.FirebaseApi
import com.ktmmoe.shared.network.requests.FCMRequest
import com.ktmmoe.shared.network.requests.FCMRequestData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ktmmoe on 29, November, 2020
 **/
object DoctorModelImpl : DoctorModel, BaseModel(){
    override var mFirebaseApi: FirebaseApi = CloudFirebaseApiImpl
    override fun observeDoctorById(id: String, onSuccess: (Doctor) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.observeDoctorById(id, onSuccess, onFailure)
    }

    override fun getPersistedDoctor(): List<Doctor> = mDB.doctorsDao().getAllDoctors()
    override fun getSpecialities(onSuccess: (List<Speciality>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getSpecialities(onSuccess, onFailure)
    }

    override fun getDoctorByIdAndSaveToDatabase(
        id: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorById(id, {
            mDB.doctorsDao().refreshDoctors(listOf(it))
            onSuccess()
        }, onFailure)
    }

    override fun addDoctor(doctor: Doctor, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.addDoctor(doctor, onSuccess, onFailure)
    }

    override fun addPatientRecentDoctor(patient: Patient, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.addPatient(patient, onSuccess, onFailure)
    }

    override fun broadcastConsultationAccept(
            consultationRequest: ConsultationRequest,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        mFirebaseApi.broadcastConsultationAccept(consultationRequest, {
            sendFCM(consultationRequest, onSuccess, onFailure)
        }, onFailure)
    }

    override fun addConsultation(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.addConsultation(consultation, onSuccess, onFailure)
    }

    @SuppressLint("CheckResult")
    private fun sendFCM(consultationRequest: ConsultationRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val request = FCMRequest(
                to = consultationRequest.patient.deviceId,
                data = FCMRequestData(
                        title = "TheCareMM",
                        body = "${consultationRequest.doctor?.name} has accepted your request."
                )
        )
        mFCMApi.sendNotification(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onSuccess() },
                        {error-> onFailure(error.localizedMessage ?: "Unknown Error") })
    }


    override fun observeConsultationRequestsBySpecialtyId(
        specialtyId: String,
        onSuccess: (List<ConsultationRequest>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.observeConsultationRequestBySpecialtyId(specialtyId, onSuccess, onFailure)
    }

    override fun observeConsultationsByDoctorId(doctorId: String, onSuccess: (List<Consultation>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.observeConsultationsByDoctorId(doctorId, onSuccess, onFailure)
    }

    override fun acceptConsultationRequest(
        consultation: Consultation,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.acceptConsultationRequest(consultation, onSuccess, onFailure)
    }

    override fun givePrescriptionsAndEndConversation(
        consultation: Consultation,
        prescriptions: List<PrescriptMedicine>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.givePrescriptionsAndEndConversation(consultation, prescriptions, onSuccess, onFailure)
    }

    override fun observeConsultationChatMessages(consultationId: String, onSuccess: (List<ChatMessage>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.observeConsultationChatMessages(consultationId, onSuccess, onFailure)
    }

    override fun sendMessageToConversation(consultation: Consultation, chatMessage: ChatMessage, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.sendChatMessageToConsultation(consultation, chatMessage, onSuccess, onFailure)
    }

    override fun getPrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getPrescriptionsByConsultationId(consultationId, onSuccess, onFailure)
    }

    override fun observePrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.observePrescriptionsByConsultationId(consultationId, onSuccess, onFailure)
    }

    override fun getMedicinesBySpecialtyId(specialtyId: String, onSuccess: (List<Medicine>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getMedicinesBySpeciality(specialtyId,onSuccess,onFailure)
    }

    override fun getEasyQuestionsBySpecialtyId(specialtyId: String, onSuccess: (List<String>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getEasyQuestionsBySpecialty(specialtyId, onSuccess, onFailure)
    }

    override fun addConsultationRemark(consultationId: String, remark: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.addConsultationRemark(consultationId, remark, onSuccess, onFailure)
    }

    override fun getConsultationRemark(consultationId: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getConsultationRemark(consultationId, onSuccess, onFailure)
    }

    override fun updateConsultationAfterEnded(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.updateConsultation(consultation, onSuccess, onFailure)
    }

    override fun uploadImage(image: Bitmap, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        PatientModelImpl.mFirebaseApi.uploadImage(image, onSuccess, onFailure)
    }
}