package com.ktmmoe.shared.data.vos

import java.io.Serializable

/**
 * Created by ktmmoe on 30, November, 2020
 **/
data class ConsultationRequest(
    var id: String = "",
    var specialtyId: String = "",
    var patient: Patient = Patient(),
    var doctor: Doctor? = null,
    var patientId: String = "",
    var doctorId: String = "",
    var dateTime: String = "",
    var accepted: Boolean = false
) : Serializable

fun Consultation.getRequest(): ConsultationRequest = ConsultationRequest(
        id, specialtyId, patient, doctor, patientId, doctorId
)

fun ConsultationRequest.consultationRequestMap(): Map<String, Any?> =
    this.doctor?.let {
        hashMapOf(
                "id" to this.id,
                "specialtyId" to this.specialtyId,
                "patient" to this.patient,
                "doctor" to it,
                "patientId" to this.patientId,
                "doctorId" to this.doctorId,
                "dateTime" to this.dateTime,
                "accepted" to this.accepted
        )
    } ?: hashMapOf(
            "id" to this.id,
            "specialtyId" to this.specialtyId,
            "patient" to this.patient,
            "patientId" to this.patientId,
            "dateTime" to this.dateTime,
            "accepted" to this.accepted
    )

fun Map<String, Any?>.consultationRequest(): ConsultationRequest {
    val consultationRequest = ConsultationRequest()
    if (this.containsKey("doctorId")) {
        consultationRequest.doctorId = this["doctorId"] as String
        consultationRequest.doctor = (this["doctor"] as HashMap<String, Any>).doctor()
    }
    consultationRequest.id = this["id"] as String
    consultationRequest.specialtyId = this["specialtyId"] as String
    consultationRequest.patient = (this["patient"] as HashMap<String, Any>).patient()
    consultationRequest.patientId = this["patientId"] as String
    consultationRequest.dateTime = this["dateTime"] as String
    consultationRequest.accepted = this["accepted"] as Boolean
    return consultationRequest
}
