package com.ktmmoe.shared.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ktmmoe.shared.data.vos.Address
import com.ktmmoe.shared.data.vos.CaseSummary
import com.ktmmoe.shared.data.vos.Medicine
import com.ktmmoe.shared.data.vos.SpecialQuestion

/**
 * Created by ktmmoe on 05, December, 2020
 **/
class CaseSummaryListTypeConverter {
    @TypeConverter
    fun toString(list: List<CaseSummary>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(jsonString: String): List<CaseSummary> {
        val list = object : TypeToken<List<CaseSummary>>() {}.type
        return Gson().fromJson(jsonString, list)
    }
}