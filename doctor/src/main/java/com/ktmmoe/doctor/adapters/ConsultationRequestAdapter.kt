package com.ktmmoe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.viewholders.*
import com.ktmmoe.shared.adapters.BaseRecyclerAdapter
import com.ktmmoe.shared.data.vos.ConsultationRequest

/**
 * Created by ktmmoe on 11, December, 2020
 **/
class ConsultationRequestAdapter(private val mNewRequestDelegate: NewConsultationRequestViewHolderDelegate, private val mOldRequestDelegate: OldConsultationRequestViewHolderDelegate): BaseRecyclerAdapter<ConsultationRequestViewHolder, ConsultationRequest>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationRequestViewHolder {
        return if (viewType == 0)
            NewConsultationRequestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_new_consultation_request, parent, false), mNewRequestDelegate)
        else OldConsultationRequestViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_old_consultation_request, parent, false), mOldRequestDelegate)
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData[position].doctorId.isNullOrEmpty()) 0 else 1
    }
}