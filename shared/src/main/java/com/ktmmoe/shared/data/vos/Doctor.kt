package com.ktmmoe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.ktmmoe.shared.persistance.typeconverters.AddressTypeConverter
import com.ktmmoe.shared.utils.doctor1
import java.io.Serializable

/**
 * Created by ktmmoe on 29, November, 2020
 **/
@IgnoreExtraProperties
@Entity(tableName = "doctors")
@TypeConverters(AddressTypeConverter::class)
data class Doctor (
        @PrimaryKey(autoGenerate = false) var id: String = "",
        var name: String = "",
        var mail: String = "",
        var image: String = "",
        var phone: String = "",
        var specialtyName: String = "",
        var specialtyId: String = "",
        var dob: String = "",
        var experience: String = "",
        var gender: String = "",
        var address: String = "",
        var degrees: String = "",
        var profile: String = "",
        var deviceId: String =""
): Serializable

fun List<Map<String, Any>>.doctorList(): List<Doctor> = this.map {
    it.doctor()
}

fun Map<String, Any>.doctor(): Doctor = Doctor(
        id = this["id"].toString(),
        name = this["name"].toString(),
        mail = this["mail"].toString(),
        image = this["image"].toString(),
        phone = this["phone"].toString(),
        specialtyName = this["specialtyName"].toString(),
        specialtyId = this["specialtyId"].toString(),
        dob = this["dob"].toString(),
        experience = this["experience"].toString(),
        gender = this["gender"].toString(),
        address = this["address"].toString(),
        degrees = this["degrees"].toString(),
        profile = this["profile"].toString(),
        deviceId = this["deviceId"].toString()
)

fun Doctor.doctorMap(): Map<String, Any> = hashMapOf(
        "id" to this.id,
        "name" to this.name,
        "mail" to this.mail,
        "image" to this.image,
        "phone" to this.phone,
        "specialtyName" to this.specialtyName,
        "specialtyId" to this.specialtyId,
        "dob" to this.dob,
        "experience" to this.experience,
        "gender" to this.gender,
        "address" to this.address,
        "degrees" to this.degrees,
        "profile" to this.profile,
        "deviceId" to this.deviceId
)