package com.ktmmoe.doctor.viewholders

import android.view.View
import com.ktmmoe.doctor.R
import com.ktmmoe.shared.data.vos.ConsultationRequest
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_new_consultation_request.view.*

/**
 * Created by ktmmoe on 11, December, 2020
 **/
class NewConsultationRequestViewHolder (private val itemView: View, private val mDelegate: NewConsultationRequestViewHolderDelegate) : ConsultationRequestViewHolder(itemView) {
    override fun bindData(data: ConsultationRequest) {
        itemView.ivPatient.load(data.patient.image)
        itemView.tvPatientName.text = data.patient.name
        itemView.tvPatientDob.text = String.format("${itemView.context.resources.getString(R.string.dob)} : %s", data.patient.dob)

        itemView.btnSkip.setOnClickListener { mDelegate.onTapSkipNewRequest() }
        itemView.btnMessage.setOnClickListener { mDelegate.onTapAcceptNewRequest(data) }
    }
}

interface NewConsultationRequestViewHolderDelegate {
    fun onTapSkipNewRequest()
    fun onTapAcceptNewRequest(consultationRequest: ConsultationRequest)
}