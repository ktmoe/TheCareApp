package com.ktmmoe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.ktmmoe.shared.persistance.typeconverters.AddressListTypeConverter
import com.ktmmoe.shared.persistance.typeconverters.CaseSummaryListTypeConverter
import com.ktmmoe.shared.persistance.typeconverters.DoctorListTypeConverter
import com.ktmmoe.shared.utils.doctor3
import java.io.Serializable

/**
 * Created by ktmmoe on 01, December, 2020
 **/
@IgnoreExtraProperties
@Entity(tableName = "patients")
@TypeConverters(DoctorListTypeConverter::class, CaseSummaryListTypeConverter::class, AddressListTypeConverter::class)
data class Patient(
        @PrimaryKey(autoGenerate = false) var id: String = "",
        var deviceId: String = "",
        var dob: String = "",
        var phone: String = "",
        var image: String = doctor3,
        var name: String = "",
        var gender: String = "",
        var height : String = "",
        var mail: String = "",
        var bloodType: String = "",
        var allergicPrescriptions: String = "",
        var weight : String = "",
        var bloodPressure: String = "",
        var address: String = "",
        var caseSummary: List<CaseSummary> = listOf(),
        var recentlyDoctors: List<Doctor> = listOf()
): Serializable

fun Map<String, Any>.patient() = Patient(
        id = this["id"].toString(),
        deviceId = this["deviceId"].toString(),
        dob = this["dob"].toString(),
        phone = this["phone"].toString(),
        image = this["image"].toString(),
        name = this["name"].toString(),
        mail = this["mail"].toString(),
        address = this["address"].toString(),
        recentlyDoctors = (this["recentlyDoctors"] as List<HashMap<String, Any>>).doctorList(),
        height = this["height"].toString(),
        bloodType = this["bloodType"].toString(),
        weight = this["weight"].toString(),
        bloodPressure = this["bloodPressure"].toString(),
        allergicPrescriptions = this["allergicPrescriptions"].toString(),
        caseSummary = (this["caseSummary"] as List<HashMap<String, Any>>).caseSummaryList()
)

fun Patient.patientMap(): Map<String, Any> = hashMapOf(
        "id" to this.id,
        "deviceId" to this.deviceId,
        "dob" to this.dob,
        "phone" to this.phone,
        "image" to this.image,
        "name" to this.name,
        "mail" to this.mail,
        "address" to this.address,
        "recentlyDoctors" to this.recentlyDoctors,
        "height" to this.height,
        "bloodType" to this.bloodType,
        "weight" to this.weight,
        "bloodPressure" to this.bloodPressure,
        "allergicPrescriptions" to this.allergicPrescriptions,
        "caseSummary" to this.caseSummary
)
