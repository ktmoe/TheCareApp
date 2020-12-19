package com.ktmmoe.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.patient.R
import com.ktmmoe.patient.viewholders.ConsultationHistoryViewHolder
import com.ktmmoe.patient.viewholders.ConsultationHistoryViewHolderDelegate
import com.ktmmoe.shared.adapters.BaseRecyclerAdapter
import com.ktmmoe.shared.data.vos.Consultation

/**
 * Created by ktmmoe on 13, December, 2020
 **/
class ConsultationHistoryAdapter(private val mDelegate: ConsultationHistoryViewHolderDelegate): BaseRecyclerAdapter<ConsultationHistoryViewHolder, Consultation>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationHistoryViewHolder =
            ConsultationHistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_consultation_history, parent, false), mDelegate)
}