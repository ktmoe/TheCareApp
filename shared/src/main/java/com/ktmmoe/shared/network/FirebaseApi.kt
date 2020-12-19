package com.ktmmoe.shared.network

import android.graphics.Bitmap
import com.ktmmoe.shared.data.vos.*

/**
 * Created by ktmmoe on 29, November, 2020
 **/
interface FirebaseApi {
    fun observePatientById(id: String, onSuccess: (Patient) -> Unit, onFailure: (String) -> Unit)
    fun observeDoctorById(id: String, onSuccess: (Doctor) -> Unit, onFailure: (String) -> Unit)
    fun getPatientById(id: String, onSuccess: (Patient) -> Unit, onFailure: (String) -> Unit)
    fun getDoctorById(id: String, onSuccess: (Doctor) -> Unit, onFailure: (String) -> Unit)

    fun getSpecialities(onSuccess: (List<Speciality>)->Unit, onFailure: (String)->Unit)
    fun getGeneralQuestions(onSuccess: (List<GeneralQuestion>) -> Unit, onFailure: (String) -> Unit)

    fun addDoctor(doctor: Doctor, onSuccess:()->Unit, onFailure: (String) -> Unit)
    fun addPatient(patient: Patient, onSuccess:()->Unit, onFailure: (String) -> Unit)
    fun getSpecialQuestionsBySpeciality(speciality: String, onSuccess: (List<SpecialQuestion>) -> Unit, onFailure: (String) -> Unit)

    fun getDoctorsBySpeciality(specialtyId: String, onSuccess: (List<Doctor>) -> Unit, onFailure: (String) -> Unit)

    fun getMedicinesBySpeciality(speciality: String, onSuccess: (List<Medicine>) -> Unit, onFailure: (String) -> Unit)

    fun getEasyQuestionsBySpecialty(speciality: String, onSuccess: (List<String>) -> Unit, onFailure: (String) -> Unit)

//    fun broadcastConsultationRequestSpecialty(consultationRequest: ConsultationRequestSpeciality, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun broadcastConsultationAccept(consultationRequest: ConsultationRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addConsultation(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun broadcastConsultationRequest(consultationRequest: ConsultationRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun observeConsultationsByPatientId(patientId: String, onSuccess: (List<Consultation>) -> Unit, onFailure: (String) -> Unit)
    fun observeConsultationsByDoctorId(doctorId: String, onSuccess: (List<Consultation>) -> Unit, onFailure: (String) -> Unit)
    fun observeConsultationRequestBySpecialtyId(specialtyId: String, onSuccess: (List<ConsultationRequest>) -> Unit, onFailure: (String) -> Unit)

    fun acceptConsultationRequest(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun observeConsultationChatMessages(consultationId: String, onSuccess: (List<ChatMessage>) -> Unit, onFailure: (String) -> Unit)

    fun checkout(checkout: Checkout, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun markConsultationStarted(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun sendChatMessageToConsultation(consultation: Consultation, chatMessage: ChatMessage, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun givePrescriptionsAndEndConversation(consultation: Consultation, prescriptions: List<PrescriptMedicine>, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun updateConsultation(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun addConsultationRemark(consultationId: String, remark: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getConsultationRemark(consultationId: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit)

    fun getPrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit)
    fun observePrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit)

    fun uploadImage(image: Bitmap, onSuccess: (String) -> Unit, onFailure: (String) -> Unit)
}