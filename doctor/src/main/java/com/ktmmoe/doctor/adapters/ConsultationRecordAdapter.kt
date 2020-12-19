package com.ktmmoe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.viewholders.ConsultationRecordItemDelegate
import com.ktmmoe.doctor.viewholders.ConsultationRecordViewHolder
import com.ktmmoe.shared.adapters.BaseRecyclerAdapter
import com.ktmmoe.shared.data.vos.Consultation

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class ConsultationRecordAdapter(private val delegate: ConsultationRecordItemDelegate): BaseRecyclerAdapter<ConsultationRecordViewHolder, Consultation>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationRecordViewHolder =
            ConsultationRecordViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_consultation_record, parent, false), delegate)
}