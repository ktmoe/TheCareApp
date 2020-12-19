package com.ktmmoe.shared.data.models.impls

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.ktmmoe.shared.data.models.BaseModel
import com.ktmmoe.shared.data.models.PatientModel
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
object PatientModelImpl: PatientModel, BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirebaseApiImpl
    override fun observePatientById(id: String, onSuccess: (Patient) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.observePatientById(id, onSuccess, onFailure)
    }

    override fun getPersistedPatient(): List<Patient> = mDB.patientDao().getAllPatients()

    override fun getPersistedSpecialities(): LiveData<List<Speciality>> = mDB.specialitiesDao().getAllSpecialities()

    override fun getSpecialitiesAndSaveToDatabase(onFailure: (String) -> Unit) {
        mFirebaseApi.getSpecialities({
            mDB.specialitiesDao().refreshSpecialities(it)
        }, onFailure)
    }

    override fun getMedicinesBySpeciality(
        speciality: String,
        onSuccess: (List<Medicine>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getMedicinesBySpeciality(speciality, onSuccess, onFailure)
    }

    override fun getSpecialQuestionsBySpeciality(
        speciality: String,
        onSuccess: (List<SpecialQuestion>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialQuestionsBySpeciality(speciality, onSuccess, onFailure)
    }

    override fun getGeneralQuestions(
        onSuccess: (List<GeneralQuestion>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getGeneralQuestions(onSuccess, onFailure)
    }

    override fun getDoctorBySpeciality(specialtyId: String, onSuccess: (List<Doctor>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getDoctorsBySpeciality(specialtyId, onSuccess, onFailure)
    }

    override fun getPatientByIdAndSaveToDatabase(
        id: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPatientById(id, {
            mDB.patientDao().refreshPatients(listOf(it))
            onSuccess()
        }, onFailure)
    }

    override fun addPatient(patient: Patient, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.addPatient(patient, onSuccess, onFailure)
    }

    override fun observeConsultationsByPatientId(patientId: String, onSuccess: (List<Consultation>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.observeConsultationsByPatientId(patientId, onSuccess, onFailure)
    }

    override fun broadcastConsultationRequest(
        consultationRequest: ConsultationRequest,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.broadcastConsultationRequest(consultationRequest, {
            sendFCM(consultationRequest.specialtyId, onSuccess, onFailure)
        }, onFailure)
    }

    override fun observeConsultationChatMessages(consultationId: String, onSuccess: (List<ChatMessage>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.observeConsultationChatMessages(consultationId, onSuccess, onFailure)
    }

    override fun markConsultationStarted(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.markConsultationStarted(consultation, onSuccess, onFailure)
    }

    @SuppressLint("CheckResult")
    private fun sendFCM(specialty: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getDoctorsBySpeciality(specialty, {
            it.forEach { doctor ->
                val request = FCMRequest(
                    to = doctor.deviceId,
                    data = FCMRequestData(
                            title = "TheCareApp",
                            body = "There is a consultation request from a patient."
                    )
                )
                mFCMApi.sendNotification(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onSuccess() },
                        {error-> onFailure(error.localizedMessage ?: "Unknown Error") })
            }
        }, onFailure)
    }

    override fun sendMessageToConversation(consultation: Consultation, chatMessage: ChatMessage, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.sendChatMessageToConsultation( consultation, chatMessage, onSuccess, onFailure)
    }

    override fun getPrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getPrescriptionsByConsultationId(consultationId, onSuccess, onFailure)
    }

    override fun observePrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.observePrescriptionsByConsultationId(consultationId, onSuccess, onFailure)
    }

    override fun uploadImage(image: Bitmap, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.uploadImage(image, onSuccess, onFailure)
    }

    override fun checkout(checkout: Checkout, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.checkout(checkout, onSuccess, onFailure)
    }
}