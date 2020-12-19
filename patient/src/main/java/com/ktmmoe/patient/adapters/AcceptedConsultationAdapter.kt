package com.ktmmoe.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.patient.R
import com.ktmmoe.patient.viewholders.AcceptedConsultationViewHolder
import com.ktmmoe.patient.viewholders.AcceptedConsultationViewHolderDelegate
import com.ktmmoe.shared.adapters.BaseRecyclerAdapter
import com.ktmmoe.shared.data.vos.Consultation

/**
 * Created by ktmmoe on 12, December, 2020
 **/
class AcceptedConsultationAdapter(private val mDelegate: AcceptedConsultationViewHolderDelegate): BaseRecyclerAdapter<AcceptedConsultationViewHolder, Consultation>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcceptedConsultationViewHolder =
            AcceptedConsultationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_accepted_request, parent, false), mDelegate)
}