package com.ktmmoe.shared.viewholders

import android.view.View
import com.ktmmoe.shared.data.vos.ChatMessage
import com.ktmmoe.shared.utils.load
import kotlinx.android.synthetic.main.item_receiver_message.view.*

/**
 * Created by ktmmoe on 13, December, 2020
 **/
class ReceiverChatMessageViewHolder(private val itemView: View, isLast:Boolean) : ChatMessageViewHolder(itemView) {
    override fun bindData(data: ChatMessage) {
        itemView.message.text = data.textMessage
        itemView.ivSender.load(data.sentBy.image)
        itemView.tvSentTime.text = data.sentAt
    }
}