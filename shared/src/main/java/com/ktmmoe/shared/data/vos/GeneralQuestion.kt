package com.ktmmoe.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "generalQuestions")
data class GeneralQuestion(
    @PrimaryKey(autoGenerate = false) var id: String = "",
    var isOneTime: Boolean = false,
    var name: String = "သီဟိုဠ်မှ ဉာဏ်ကြီးရှင်သည် အာယုဝဍ္ဎနဆေးညွှန်းစာကို ဇလွန်ဈေးဘေး ဗာဒံပင်ထက် အဓိဋ္ဌာန်လျက် ဂဃနဏဖတ်ခဲ့သည်။",
    var answer: String = ""
)

fun Map<String, Any>.generalQuestion(): GeneralQuestion = GeneralQuestion(
    id = this["id"] as String,
    isOneTime = this["isOneTime"] as Boolean,
    name = this["name"] as String
)

fun Map<String, Any>.generalQuestionWithAnswer(): GeneralQuestion = GeneralQuestion(
        id = this["id"] as String,
        isOneTime = this["isOneTime"] as Boolean,
        name = this["name"] as String,
        answer = this["answer"] as String
)