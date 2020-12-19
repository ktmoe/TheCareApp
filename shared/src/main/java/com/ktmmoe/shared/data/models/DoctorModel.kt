package com.ktmmoe.shared.data.models

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.network.FirebaseApi
import com.ktmmoe.shared.network.requests.FCMRequest
import com.ktmmoe.shared.network.requests.FCMRequestData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ktmmoe on 29, November, 2020
 **/
interface DoctorModel {
    fun observeDoctorById(id: String, onSuccess: (Doctor) -> Unit, onFailure: (String) -> Unit)
    fun getPersistedDoctor(): List<Doctor>
    fun getSpecialities(onSuccess: (List<Speciality>) -> Unit, onFailure: (String) -> Unit)

    fun getDoctorByIdAndSaveToDatabase(id: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addDoctor(doctor: Doctor, onSuccess:()->Unit, onFailure: (String) -> Unit)

    fun addPatientRecentDoctor(patient: Patient, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun broadcastConsultationAccept(consultationRequest: ConsultationRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addConsultation(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun observeConsultationRequestsBySpecialtyId(specialtyId: String, onSuccess: (List<ConsultationRequest>) -> Unit, onFailure: (String) -> Unit)
    fun observeConsultationsByDoctorId(doctorId: String, onSuccess: (List<Consultation>) -> Unit, onFailure: (String) -> Unit)

    fun acceptConsultationRequest(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun givePrescriptionsAndEndConversation(consultation: Consultation, prescriptions: List<PrescriptMedicine>, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun observeConsultationChatMessages(consultationId: String, onSuccess: (List<ChatMessage>) -> Unit, onFailure: (String) -> Unit)
    fun sendMessageToConversation(consultation: Consultation, chatMessage: ChatMessage, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getPrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit)
    fun observePrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit)

    fun getMedicinesBySpecialtyId(specialtyId: String, onSuccess: (List<Medicine>) -> Unit, onFailure: (String) -> Unit)
    fun getEasyQuestionsBySpecialtyId(specialtyId: String, onSuccess: (List<String>) -> Unit, onFailure: (String) -> Unit)
    fun addConsultationRemark(consultationId: String, remark: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getConsultationRemark(consultationId: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit)
    fun updateConsultationAfterEnded(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun uploadImage(image: Bitmap, onSuccess: (String)-> Unit, onFailure: (String) -> Unit)
}
