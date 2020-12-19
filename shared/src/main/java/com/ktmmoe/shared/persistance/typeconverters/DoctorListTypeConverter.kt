package com.ktmmoe.shared.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ktmmoe.shared.data.vos.Address
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.Medicine
import com.ktmmoe.shared.data.vos.SpecialQuestion

/**
 * Created by ktmmoe on 05, December, 2020
 **/
class DoctorListTypeConverter {
    @TypeConverter
    fun toString(list: List<Doctor>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(jsonString: String): List<Doctor> {
        val list = object : TypeToken<List<Doctor>>() {}.type
        return Gson().fromJson(jsonString, list)
    }
}