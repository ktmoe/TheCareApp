package com.ktmmoe.patient.viewholders

import android.view.View
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_recent_doctor.view.*

/**
 * Created by ktmmoe on 29, November, 2020
 **/
class RecentDoctorViewHolder(itemView: View, private val delegate: RecentDoctorDelegate): BaseViewHolder<Doctor>(itemView) {

    override fun bindData(data: Doctor) {
        itemView.ivDoctor.load(data.image)

        itemView.tvDoctorName.text = data.name

        itemView.tvDoctorSpeciality.text = data.specialtyName

        itemView.setOnClickListener {
            delegate.onTapRecentDoctor(data)
        }
    }
}

interface RecentDoctorDelegate{
    fun onTapRecentDoctor(data: Doctor)
}