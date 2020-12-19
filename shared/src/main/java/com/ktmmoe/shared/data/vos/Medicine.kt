package com.ktmmoe.shared.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
@Entity
data class Medicine(
    var id: String = "",
    var name: String = "",
    var price: Int = 0
): Serializable

fun Map<String, Any>.medicine() = Medicine(
    id = this["id"].toString(),
    name = this["name"].toString(),
    price = (this["price"].toString()).toInt()
)

fun Medicine.medicineMap() = hashMapOf(
        "id" to this.id,
        "name" to this.name,
        "price" to this.price
)