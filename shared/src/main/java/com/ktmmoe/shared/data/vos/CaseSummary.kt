package com.ktmmoe.shared.data.vos

import androidx.room.Entity
import com.google.firebase.firestore.IgnoreExtraProperties
import java.io.Serializable

/**
 * Created by ktmmoe on 30, November, 2020
 **/
@IgnoreExtraProperties
@Entity
data class CaseSummary(
    var id: String = "",
    var question: String = "သီဟိုဠ်မှ ဉာဏ်ကြီးရှင်သည် အာယုဝဍ္ဎနဆေးညွှန်းစာကို ဇလွန်ဈေးဘေး ဗာဒံပင်ထက် အဓိဋ္ဌာန်လျက် ဂဃနဏဖတ်ခဲ့သည်။",
    var answer: String = "ဂဃနဏဖတ်ခဲ့သည်။"
): Serializable

fun HashMap<String, Any>.caseSummary(): CaseSummary = CaseSummary(
        id= this["id"] as String,
        question = this["question"] as String,
        answer = this["answer"] as String
)

fun List<HashMap<String, Any>>.caseSummaryList(): List<CaseSummary> =
        this.map {
            it.caseSummary()
        }
