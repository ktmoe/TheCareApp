package com.ktmmoe.patient.viewholders

import android.view.View
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_consultation_history.view.*

/**
 * Created by ktmmoe on 13, December, 2020
 **/
class ConsultationHistoryViewHolder(private val itemView: View, private val mDelegate: ConsultationHistoryViewHolderDelegate) : BaseViewHolder<Consultation>(itemView) {
    override fun bindData(data: Consultation) {
        itemView.tvConsultedDate.text = data.consultedDate
        itemView.ivDoctor.load(data.doctor.image)
        itemView.tvDoctorName.text = data.doctor.name
        itemView.tvDoctorSpeciality.text = data.doctor.specialtyName

        itemView.medicalRecord.setOnClickListener { mDelegate.onTapMedicalRecord(data) }
        itemView.prescriptions.setOnClickListener { mDelegate.onTapPrescriptions(data) }
        itemView.setOnClickListener { mDelegate.onTapRecord(data) }
    }
}

interface ConsultationHistoryViewHolderDelegate {
    fun onTapMedicalRecord(consultation: Consultation)
    fun onTapPrescriptions(consultation: Consultation)
    fun onTapRecord(consultation: Consultation)
}