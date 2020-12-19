package com.ktmmoe.shared.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ktmmoe.shared.data.vos.Address
import com.ktmmoe.shared.data.vos.Medicine
import com.ktmmoe.shared.data.vos.SpecialQuestion

/**
 * Created by ktmmoe on 05, December, 2020
 **/
class AddressListTypeConverter {
    @TypeConverter
    fun toString(list: List<Address>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(jsonString: String): List<Address> {
        val list = object : TypeToken<List<Address>>() {}.type
        return Gson().fromJson(jsonString, list)
    }
}