package com.ktmmoe.shared.network

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.ktmmoe.shared.data.vos.*
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Created by ktmmoe on 29, November, 2020
 **/
object CloudFirebaseApiImpl: FirebaseApi {
    private val fireStore = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference

    override fun getSpecialities(
        onSuccess: (List<Speciality>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        fireStore.collection("specialities")
            .get().addOnSuccessListener { result->
                val specialities: MutableList<Speciality> = mutableListOf()
                for(document in result) {
                    val data = document.data
                    val speciality = data.speciality()
                    specialities.add(speciality)
                }
                onSuccess(specialities)
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?: "Please check connection")
            }
    }

    override fun getGeneralQuestions(
        onSuccess: (List<GeneralQuestion>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        fireStore.collection("general-question-template")
            .get().addOnSuccessListener { result->
                val generalQuestions: MutableList<GeneralQuestion> = mutableListOf()
                for (document in result) {
                    generalQuestions.add(document.data.generalQuestion())
                }
                onSuccess.invoke(generalQuestions)
            }.addOnFailureListener { onFailure.invoke(it.localizedMessage ?: "") }
    }

    override fun getDoctorsBySpeciality(specialtyId: String, onSuccess: (List<Doctor>) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("doctor")
                .whereEqualTo("specialtyId", specialtyId)
                .get().addOnSuccessListener { result->
                val doctors: MutableList<Doctor> = mutableListOf()
                for (document in result) {
                    doctors.add(document.data.doctor())
                }
                onSuccess.invoke(doctors)
            }.addOnFailureListener { onFailure.invoke(it.localizedMessage ?: "") }
    }

    override fun observePatientById(id: String, onSuccess: (Patient) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("patient")
                .whereEqualTo("id", id)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        onFailure(error.localizedMessage ?: "")
                    }
                    else {
                        val patient : MutableList<Patient> = mutableListOf()
                        value?.let { result->
                            for (document in result.documents) {
                                document.data?.patient()?.let {
                                    patient.add(it)
                                }
                            }
                        }
                        onSuccess(patient.first())
                    }
                }
    }

    override fun observeDoctorById(id: String, onSuccess: (Doctor) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("doctor")
                .whereEqualTo("id", id)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        onFailure(error.localizedMessage ?: "")
                    }
                    else {
                        val doctor : MutableList<Doctor> = mutableListOf()
                        value?.let { result->
                            for (document in result.documents) {
                                document.data?.doctor()?.let {
                                    doctor.add(it)
                                }
                            }
                        }
                        if (doctor.isNotEmpty())
                            onSuccess(doctor.first())
                    }
                }
    }

    override fun getPatientById(
        id: String,
        onSuccess: (Patient) -> Unit,
        onFailure: (String) -> Unit
    ) {
        fireStore.collection("patient")
            .document(id)
            .get().addOnSuccessListener { document->
                document.data?.patient()?.let {
                    onSuccess.invoke(it)
                }
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?: "") }
    }

    override fun getDoctorById(
        id: String,
        onSuccess: (Doctor) -> Unit,
        onFailure: (String) -> Unit
    ) {
        fireStore.collection("doctor")
            .document(id)
            .get().addOnSuccessListener { document->
                document.data?.doctor()?.let {
                    onSuccess.invoke(it)
                } ?: onFailure("User does not exist.")
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?: "") }
    }

    override fun addDoctor(doctor: Doctor, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val doctorMap = doctor.doctorMap()

        fireStore.collection("doctor")
            .document(doctor.id)
            .set(doctorMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?:"add failed.")
            }
    }

    override fun addPatient(patient: Patient, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val patientMap = patient.patientMap()

        fireStore.collection("patient")
            .document(patient.id)
            .set(patientMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure( it.localizedMessage ?:"add failed.")
            }
    }

    override fun broadcastConsultationRequest(
        consultationRequest: ConsultationRequest,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val consultationRequestMap = consultationRequest.consultationRequestMap()

        fireStore.collection("consultation-request")
            .document(consultationRequest.id)
            .set(consultationRequestMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?:"add failed.")
            }
    }

    override fun broadcastConsultationAccept(consultationRequest: ConsultationRequest, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val consultationRequestMap = consultationRequest.consultationRequestMap()

        fireStore.collection("consultation-request")
                .document(consultationRequest.id)
                .set(consultationRequestMap)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener {
                    onFailure(it.localizedMessage ?:"add failed.")
                }
    }

    override fun addConsultation(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val consultationMap = consultation.consultationMap()

        fireStore.collection("consultation")
                .document(consultation.id)
                .set(consultationMap)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener {
                    onFailure(it.localizedMessage ?:"add failed.")
                }
    }

    override fun observeConsultationsByPatientId(patientId: String, onSuccess: (List<Consultation>) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("consultation")
                .whereEqualTo("patientId", patientId)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        onFailure(error.localizedMessage ?: "")
                    }
                    else {
                        val consultations : MutableList<Consultation> = mutableListOf()
                        value?.let { result->
                            for (document in result.documents) {
                                document.data?.consultation()?.let {
                                    val data = it
                                    consultations.add(it)
                                }
                            }
                        }
                        onSuccess(consultations)
                    }
                }
    }

    override fun observeConsultationsByDoctorId(doctorId: String, onSuccess: (List<Consultation>) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("consultation")
                .whereEqualTo("doctorId", doctorId)
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        onFailure(error.localizedMessage ?: "")
                    }
                    else {
                        val consultations : MutableList<Consultation> = mutableListOf()
                        value?.let { result->
                            for (document in result.documents) {
                                document.data?.consultation()?.let {
                                    consultations.add(it)
                                }
                            }
                        }
                        onSuccess(consultations)
                    }
                }
    }

    override fun observeConsultationRequestBySpecialtyId(specialtyId: String, onSuccess: (List<ConsultationRequest>) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("consultation-request")
            .whereEqualTo("specialtyId", specialtyId)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailure(error.localizedMessage ?: "")
                }
                else {
                    val consultationRequests : MutableList<ConsultationRequest> = mutableListOf()
                    value?.let { result->
                        for (document in result.documents) {
                            document.data?.consultationRequest()?.let {
                                consultationRequests.add(it)
                            }
                        }
                    }
                    onSuccess(consultationRequests)
                }
            }
    }

    override fun acceptConsultationRequest(
        consultation: Consultation,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val consultationMap = consultation.consultationMap()

        fireStore.collection("consultation")
            .document(consultation.id)
            .set(consultationMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure( it.localizedMessage ?:"add failed.")
            }
    }

    override fun observeConsultationChatMessages(consultationId: String, onSuccess: (List<ChatMessage>) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("consultation")
                .document(consultationId)
                .collection("chatMessage")
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        onFailure(error.localizedMessage ?: "")
                    }
                    else {
                        val chatMessages : MutableList<ChatMessage> = mutableListOf()
                        value?.let { result->
                            for (document in result.documents) {
                                document.data?.chatMessage()?.let {
                                    chatMessages.add(it)
                                }
                            }
                        }
                        onSuccess(chatMessages)
                    }
                }
    }

    override fun checkout(checkout: Checkout, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val checkoutMap = checkout.checkoutMap()

        fireStore.collection("checkout")
            .document(checkout.id)
            .set(checkoutMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?:"add failed.")
            }
    }

    override fun markConsultationStarted(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val consultationMap = consultation.consultationMap()

        fireStore.collection("consultation")
                .document(consultation.id)
                .set(consultationMap)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener {
                    onFailure( it.localizedMessage ?:"add failed.")
                }
    }

    override fun sendChatMessageToConsultation(consultation: Consultation, chatMessage: ChatMessage, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val chatMessageMap = chatMessage.chatMessageMap()

        fireStore.collection("consultation/${consultation.id}/chatMessage")
                .add(chatMessageMap)
                .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?:"add failed.")
            }
    }

    override fun givePrescriptionsAndEndConversation(
        consultation: Consultation,
        prescriptions: List<PrescriptMedicine>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val prescriptMedicineListMap = prescriptions.prescriptMedicineListMap()

        fireStore.collection("consultation/${consultation.id}/prescription")
            .add(prescriptMedicineListMap)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it.localizedMessage ?:"add failed.")
            }
    }

    override fun updateConsultation(consultation: Consultation, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val consultationMap = consultation.consultationMap()

        fireStore.collection("consultation")
                .document(consultation.id)
                .update(consultationMap)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { onFailure(it.localizedMessage?:"") }
    }

    override fun addConsultationRemark(consultationId: String, remark: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val remarkMap = mapOf("remark" to remark)

        fireStore.collection("consultation/$consultationId/remark")
                .add(remarkMap)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener {
                    onFailure(it.localizedMessage ?:"add failed.")
                }
    }

    override fun getConsultationRemark(consultationId: String, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("consultation")
                .document(consultationId)
                .collection("remark")
                .get()
                .addOnSuccessListener { result->
                    var remark = ""
                    for (document in result.documents) {
                        val map = document.data as Map<*,*>
                        if (map.containsKey("remark"))
                            remark = map["remark"].toString()
                    }
                    onSuccess(remark)
                }.addOnFailureListener { onFailure(it.localizedMessage ?: "get remark failed.") }
    }

    override fun getPrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("consultation")
                .document(consultationId)
                .collection("prescription")
                .get()
                .addOnSuccessListener { result->
                    val prescriptions : MutableList<PrescriptMedicine> = mutableListOf()
                    for (document in result.documents) {
                        document.data?.prescriptMedicines()?.let {
                            prescriptions.addAll(it)
                        }
                    }
                    onSuccess(prescriptions)
                }.addOnFailureListener { onFailure(it.localizedMessage ?: "get Prescription failed.") }
    }

    override fun observePrescriptionsByConsultationId(consultationId: String, onSuccess: (List<PrescriptMedicine>) -> Unit, onFailure: (String) -> Unit) {
        fireStore.collection("consultation")
                .document(consultationId)
                .collection("prescription")
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        onFailure(error.localizedMessage ?: "")
                    }
                    else {
                        val prescriptMedicines : MutableList<PrescriptMedicine> = mutableListOf()
                        value?.let { result->
                            for (document in result.documents) {
                                document.data?.prescriptMedicines()?.let {
                                    prescriptMedicines.addAll(it)
                                }
                            }
                        }
                        onSuccess(prescriptMedicines)
                    }
                }
    }

    override fun uploadImage(
        image: Bitmap,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val data = byteArrayOutputStream.toByteArray()

        val imageRef = storageReference.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            //
        }.addOnSuccessListener {
            //
        }

        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            onSuccess(task.result.toString())
        }
    }

    override fun getMedicinesBySpeciality(speciality: String, onSuccess: (List<Medicine>) -> Unit, onFailure: (String) -> Unit){
        val medicines: MutableList<Medicine> = mutableListOf()
        fireStore.collection("specialities/S$speciality/medicine")
            .get().addOnSuccessListener { result->
                for (document in result) {
                    medicines.add(document.data.medicine())
                }
                    Log.d("MEDICINES", "$medicines")
                onSuccess.invoke(medicines)
            }
            .addOnFailureListener { onFailure.invoke(it.localizedMessage ?: "get failed") }
    }

    override fun getEasyQuestionsBySpecialty(speciality: String, onSuccess: (List<String>) -> Unit, onFailure: (String) -> Unit) {
        val questions: MutableList<String> = mutableListOf()
        fireStore.collection("specialities/S$speciality/easy-questions")
                .get().addOnSuccessListener { result->
                    for (document in result) {
                        questions.addAll(((document.data)["questions"] as List<*>).map { it.toString() })
                    }
                    onSuccess.invoke(questions)
                }
                .addOnFailureListener { onFailure.invoke(it.localizedMessage ?: "get failed") }
    }

    override fun getSpecialQuestionsBySpeciality(speciality: String, onSuccess: (List<SpecialQuestion>) -> Unit, onFailure: (String) -> Unit) {
        val specialQuestions: MutableList<SpecialQuestion> = mutableListOf()
        fireStore.collection("specialities/S${speciality}/specialQuestion")
            .get().addOnSuccessListener { result->
                for (document in result) {
                    specialQuestions.add(document.data.specialQuestion())
                }
                onSuccess.invoke(specialQuestions)
            }
            .addOnFailureListener { onFailure.invoke(it.localizedMessage ?: "get failed") }
    }
}