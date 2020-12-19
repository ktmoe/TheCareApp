package com.ktmmoe.shared.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ktmmoe.shared.data.vos.SpecialQuestion

/**
 * Created by ktmmoe on 05, December, 2020
 **/
class SpecialityQuestionListTypeConverter {
    @TypeConverter
    fun toString(list: List<SpecialQuestion>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(jsonString: String): List<SpecialQuestion> {
        val list = object : TypeToken<List<SpecialQuestion>>() {}.type
        return Gson().fromJson(jsonString, list)
    }
}