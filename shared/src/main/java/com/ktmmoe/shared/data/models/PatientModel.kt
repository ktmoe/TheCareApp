package com.ktmmoe.shared.data.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.network.FirebaseApi

/**
 * Created by ktmmoe on 29, November, 2020
 **/
interface PatientModel{
    fun observePatientById(id: String, onSuccess: (Patient) -> Unit, onFailure: (String) -> Unit)
    fun getPersistedPatient():List<Patient>
    fun getPersistedSpecialities(): LiveData<List<Speciality>>

    fun getSpecialitiesAndSaveToDatabase(onFailure: (String) -> Unit)
    fun getMedicinesBySpeciality(speciality: String, onSuccess: (List<Medicine>) -> Unit, onFailure: (String) -> Unit)
    fun getSpecialQuestionsBySpeciality(speciality: String, onSuccess: (List<SpecialQuestion>) -> Unit, onFailure: (String) -> Unit)
    fun getGeneralQuestions(onSuccess: (List<GeneralQuestion>) -> Unit, onFailure: (String) -> Unit)
    fun getDoctorBySpeciality(specialtyId: String, onSuccess: (List<Doctor>) -> Unit, onFailure: (String) -> Unit)
    fun getPatientByIdAndSaveToDatabase(id: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun addPatient(patient: Patient, onSuccess:()->Unit, onFailure: (String) -> Unit)

    fun observeConsultationsByPatientId(patientId: String, onSuccess: (List<Consultation>) -> Unit, onFailure: (String) -> Unit)
    fun broadcastConsultationRequest(consultationRequest: ConsultationRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun observeConsultationChatMessages(consultationId: String, onSuccess: (List<ChatMessage>) -> Unit, onFailure: (String) -> Unit)
    fun markConsultationStarted(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun sendMessageToConversation(consultation: Consultation, chatMessage: ChatMessage, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getPrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit)
    fun observePrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit)

    fun uploadImage(image: Bitmap, onSuccess: (String)-> Unit, onFailure: (String) -> Unit)

    fun checkout(checkout: Checkout, onSuccess: () -> Unit, onFailure: (String) -> Unit)
}