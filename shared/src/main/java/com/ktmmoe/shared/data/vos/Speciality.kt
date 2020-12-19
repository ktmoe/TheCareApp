package com.ktmmoe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties
import com.ktmmoe.shared.persistance.typeconverters.MedicineListTypeConverter
import com.ktmmoe.shared.persistance.typeconverters.SpecialityQuestionListTypeConverter
import java.io.Serializable

@IgnoreExtraProperties
@Entity(tableName = "specialities")
@TypeConverters(SpecialityQuestionListTypeConverter::class, MedicineListTypeConverter::class)
data class Speciality(
    @PrimaryKey(autoGenerate = false) var id: String = "",
    var medicines: List<Medicine> = emptyList(),
    var name: String = "",
    var image: String = "",
    var specialQuestions: List<SpecialQuestion> = listOf()
): Serializable

fun Map<String, Any>.speciality(): Speciality {
    val image = if (this.containsKey("image")) this["image"] as String else ""
    return Speciality(
        id = this["id"] as String,
        name = this["name"] as String,
        image = image
    )
}