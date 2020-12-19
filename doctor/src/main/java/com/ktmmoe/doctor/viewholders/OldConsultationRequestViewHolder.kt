package com.ktmmoe.doctor.viewholders

import android.view.View
import com.ktmmoe.doctor.R
import com.ktmmoe.shared.data.vos.ConsultationRequest
import com.ktmmoe.shared.utils.load
import kotlinx.android.synthetic.main.item_old_consultation_request.view.*

/**
 * Created by ktmmoe on 11, December, 2020
 **/
class OldConsultationRequestViewHolder (private val itemView: View, private val mDelegate: OldConsultationRequestViewHolderDelegate) : ConsultationRequestViewHolder(itemView) {
    override fun bindData(data: ConsultationRequest) {
        itemView.ivPatient.load(data.patient.image)
        itemView.tvPatientName.text = data.patient.name
        itemView.tvPatientDob.text = String.format("${itemView.context.resources.getString(R.string.dob)} : %s", data.patient.dob)

        itemView.btnLater.setOnClickListener { mDelegate.onTapLaterOldRequest() }
        itemView.btnSchedule.setOnClickListener { mDelegate.onTapScheduleOldRequest() }
        itemView.btnMessage.setOnClickListener { mDelegate.onTapAcceptOldRequest(data) }
    }
}

interface OldConsultationRequestViewHolderDelegate {
    fun onTapLaterOldRequest()
    fun onTapScheduleOldRequest()
    fun onTapAcceptOldRequest(consultationRequest: ConsultationRequest)
}