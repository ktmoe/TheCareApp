package com.ktmmoe.doctor.viewholders

import android.view.View
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_consultation_record.view.*

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class ConsultationRecordViewHolder(private val itemView: View, private val delegate: ConsultationRecordItemDelegate): BaseViewHolder<Consultation>(itemView) {

    override fun bindData(data: Consultation) {
        itemView.ivPatient.load(data.patient.image)
        itemView.tvPatientName.text = data.patient.name
        itemView.tvPatientDob.text = data.patient.dob

        itemView.btnMessage.setOnClickListener { delegate.onTapSendMessage(data) }
        itemView.btnPrescription.setOnClickListener { delegate.onTapPrescription(data) }
        itemView.btnMedicalHistory.setOnClickListener { delegate.onTapMedicalHistory(data) }
        itemView.btnRemark.setOnClickListener { delegate.onTapRemark(data) }
    }
}

interface ConsultationRecordItemDelegate {
    fun onTapSendMessage(consultationRecord: Consultation)
    fun onTapMedicalHistory(consultationRecord: Consultation)
    fun onTapPrescription(consultationRecord: Consultation)
    fun onTapRemark(consultationRecord: Consultation)
}