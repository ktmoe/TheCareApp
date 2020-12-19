package com.ktmmoe.shared.data.vos

import java.io.Serializable

/**
 * Created by ktmmoe on 10, December, 2020
 **/
data class ConsultationRequestSpeciality(
        var id: String = "",
        var specialtyId: String = "",
        var patient: Patient = Patient(),
        var dateTime: String = ""
) : Serializable

fun ConsultationRequestSpeciality.consultationRequestSpecialtyMap(): Map<String, Any> =
        hashMapOf(
                "id" to this.id,
                "specialtyId" to this.specialtyId,
                "patient" to this.patient,
                "date-time" to this.dateTime
        )