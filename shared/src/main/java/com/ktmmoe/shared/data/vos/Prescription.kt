package com.ktmmoe.shared.data.vos

import java.io.Serializable

/**
 * Created by ktmmoe on 30, November, 2020
 **/
data class Prescription(
        var medicineName: String = "Vitamin-c",
        var amount: String = "700 mg",
        var dosage: String = "1 Tablet",
        var duration: String = "3 Days",
        var time: String = "Morning/Noon/Evening",
        var repeat: String = "everyday",
        var remark: String = "Take it after meal"
): Serializable

fun Map<String, Any>.prescription(): Prescription =
        Prescription(

        )
