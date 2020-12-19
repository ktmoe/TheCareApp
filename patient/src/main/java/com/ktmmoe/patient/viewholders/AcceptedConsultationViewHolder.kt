package com.ktmmoe.patient.viewholders

import android.view.View
import com.ktmmoe.patient.R
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_accepted_request.view.*

/**
 * Created by ktmmoe on 12, December, 2020
 **/
class AcceptedConsultationViewHolder(private val itemView: View, private val mDelegate: AcceptedConsultationViewHolderDelegate): BaseViewHolder<Consultation>(itemView) {
    override fun bindData(data: Consultation) {

        itemView.tvAcceptedRequestMessage.text = String.format(itemView.context.resources.getString(R.string.accepted_request_message), data.doctor.name)
        if (data.consultationStarted) {
            itemView.tvAcceptedRequestTitle.text = itemView.context.resources.getString(R.string.continue_consultation_title)
            itemView.tvAcceptedRequestMessage.visibility = View.GONE
            itemView.tvStartConsultation.text = itemView.context.resources.getString(R.string.continue_consultation)
        }

        itemView.ivDoctor.load(data.doctor.image)
        itemView.tvDoctorName.text = data.doctor.name
        itemView.tvDoctorProfile.text = data.doctor.profile

        itemView.tvStartConsultation.setOnClickListener { mDelegate.onTapStartConsultation(data) }
    }
}

interface AcceptedConsultationViewHolderDelegate {
    fun onTapStartConsultation(consultation: Consultation)
}