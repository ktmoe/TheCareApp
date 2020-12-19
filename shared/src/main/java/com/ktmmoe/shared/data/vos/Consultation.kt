package com.ktmmoe.shared.data.vos

import java.io.Serializable

/**
 * Created by ktmmoe on 30, November, 2020
 **/
data class Consultation(
    var id: String = "",
    var specialtyId: String = "",
    var patient: Patient = Patient(),
    var doctor: Doctor = Doctor(),
    var patientId: String = "",
    var doctorId: String = "",
    var consultedDate: String = "",
    var consultationStarted: Boolean = false
): Serializable

fun Consultation.consultationMap(): Map<String, Any> = hashMapOf(
        "id" to this.id,
        "specialtyId" to this.specialtyId,
        "patient" to this.patient,
        "doctor" to this.doctor,
        "patientId" to this.patientId,
        "doctorId" to this.doctorId,
        "consultedDate" to this.consultedDate,
        "consultationStarted" to this.consultationStarted
)

fun Map<String, Any>.consultation(): Consultation= Consultation(
        id = this["id"] as String,
        specialtyId = this["specialtyId"] as String,
        patient = (this["patient"] as HashMap<String, Any>).patient(),
        doctor = (this["doctor"] as HashMap<String, Any>).doctor(),
        patientId = this["patientId"] as String,
        doctorId = this["doctorId"] as String,
        consultedDate = this["consultedDate"] as String,
        consultationStarted = if (this.containsKey("consultationStarted")) this["consultationStarted"] as Boolean else false
)
