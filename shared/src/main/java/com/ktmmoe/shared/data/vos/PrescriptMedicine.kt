package com.ktmmoe.shared.data.vos

import java.io.Serializable

/**
 * Created by ktmmoe on 02, December, 2020
 **/
data class PrescriptMedicine(
        var medicine: Medicine = Medicine(),
        var routine: Routine = Routine()
): Serializable

data class Routine(
        var tab: Int = 0,
        var duration: String = "1 Week",
        var morning: Boolean = false,
        var noon: Boolean = false,
        var night: Boolean = false,
        var afterMeal: Boolean = false,
        var beforeMeal: Boolean = false,
        var remark: String = ""
): Serializable

fun Map<String, Any>.routine()= Routine(
        tab = if (this.containsKey("tab")) this["tab"].toString().toInt() else 0,
        duration = this["duration"].toString(),
        morning = if (this.containsKey("morning")) this["morning"] as Boolean else false,
        noon = if (this.containsKey("noon")) this["noon"] as Boolean else false,
        night = if (this.containsKey("night")) this["night"] as Boolean else false,
        afterMeal = if (this.containsKey("afterMeal")) this["afterMeal"] as Boolean else false,
        beforeMeal = if (this.containsKey("beforeMeal")) this["beforeMeal"] as Boolean else false,
        remark = this["remark"].toString()
)

fun Routine.routineMap() = hashMapOf(
        "tab" to this.tab,
        "duration" to this.duration,
        "morning" to this.morning,
        "noon" to this.noon,
        "night" to this.night,
        "afterMeal" to this.afterMeal,
        "beforeMeal" to this.beforeMeal,
        "remark" to this.remark
)

fun List<PrescriptMedicine>.prescriptMedicineListMap(): Map<String, Any> {
        val map: MutableMap<String, Any> = hashMapOf()
        this.forEach {
                map[it.medicine.name] = it.prescriptMedicineMap()
        }
        return map
}

fun PrescriptMedicine.prescriptMedicineMap(): Map<String, Any> = hashMapOf(
        "medicine" to this.medicine,
        "routine" to this.routine
)

fun List<Map<String, Any>>.prescriptMedicineList(): List<PrescriptMedicine> = this.map {
        it.prescriptMedicine()
}

fun Map<String, Any>.prescriptMedicine(): PrescriptMedicine = PrescriptMedicine(
        medicine = (this["medicine"] as Map<String,Any>).medicine(),
        routine = (this["routine"] as Map<String, Any>).routine()
)

fun Map<String, Any>.prescriptMedicines(): List<PrescriptMedicine> = this.values.map {any->
        (any as Map<String, Any>).prescriptMedicine()
}
