package com.ktmmoe.shared.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties
import java.io.Serializable

/**
 * Created by ktmmoe on 06, December, 2020
 **/
@IgnoreExtraProperties
@Entity
data class Address(
    var state: String = "Yangon",
    var township: String = "Ahlone",
    var fullAddress: String = "No 111 blah"
): Serializable
fun Map<String, Any>.address(): Address = Address(
    state = this["state"] as String,
    township = this["township"] as String,
    fullAddress = this["fullAddress"] as String
)
