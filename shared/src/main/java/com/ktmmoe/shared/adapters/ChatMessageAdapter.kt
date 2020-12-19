package com.ktmmoe.shared.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.shared.R
import com.ktmmoe.shared.data.vos.ChatMessage
import com.ktmmoe.shared.viewholders.ChatMessageViewHolder
import com.ktmmoe.shared.viewholders.ReceiverChatMessageViewHolder
import com.ktmmoe.shared.viewholders.SenderChatMessageViewHolder

/**
 * Created by ktmmoe on 13, December, 2020
 **/
class ChatMessageAdapter: BaseRecyclerAdapter<ChatMessageViewHolder, ChatMessage>() {
    private var chatRoomOwnerId: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        return if (viewType == 0) ReceiverChatMessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_receiver_message, parent, false), false)
        else SenderChatMessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sender_message, parent, false), false)
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData[position].sentBy.senderId == chatRoomOwnerId) 0 else 1 //if I am receiver -> 0 else 1
    }

    fun setChatRoomOwnerId(id: String) {
        chatRoomOwnerId = id
    }
}