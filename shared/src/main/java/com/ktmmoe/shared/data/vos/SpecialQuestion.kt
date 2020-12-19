package com.ktmmoe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import java.io.Serializable

/**
 * Created by ktmmoe on 03, December, 2020
 **/
@IgnoreExtraProperties
@Entity
data class SpecialQuestion(
        var id: String = "",
        var name: String = "သီဟိုဠ်မှ ဉာဏ်ကြီးရှင်သည် အာယုဝဍ္ဎနဆေးညွှန်းစာကို ဇလွန်ဈေးဘေး ဗာဒံပင်ထက် အဓိဋ္ဌာန်လျက် ဂဃနဏဖတ်ခဲ့သည်။"
): Serializable

fun Map<String, Any>.specialQuestion(): SpecialQuestion = SpecialQuestion(
        id = this["id"] as String,
        name = this["name"] as String
)