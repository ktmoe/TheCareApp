package com.ktmmoe.shared.data.vos

import com.ktmmoe.shared.utils.doctor2
import java.io.Serializable

/**
 * Created by ktmmoe on 06, December, 2020
 **/
data class ChatMessage(
        var id: String = "",
        var sentAt: String = "12:00AM",
        var sentBy: SentBy = SentBy(),
        var textMessage: String = "Hello ..."
):Serializable

data class SentBy(
        var senderId: String = "",
        var name: String = "Aung Aung",
        var image: String = doctor2
): Serializable

fun Map<String, Any>.sentBy(): SentBy = SentBy(
        senderId = this["senderId"] as String,
        name = this["name"] as String,
        image = this["image"] as String
)

fun ChatMessage.chatMessageMap(): Map<String, Any> = hashMapOf(
        "id" to this.id,
    "sentAt" to this.sentAt,
    "sentBy" to this.sentBy,
    "textMessage" to this.textMessage
)

fun Map<String, Any>.chatMessage(): ChatMessage = ChatMessage(
        id = this["id"].toString(),
        sentAt = this["sentAt"] as String,
        sentBy = (this["sentBy"] as Map<String, Any>).sentBy(),
        textMessage = this["textMessage"] as String
)