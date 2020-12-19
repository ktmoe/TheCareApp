package com.ktmmoe.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.patient.R
import com.ktmmoe.patient.viewholders.RecentDoctorDelegate
import com.ktmmoe.patient.viewholders.RecentDoctorViewHolder
import com.ktmmoe.shared.adapters.BaseRecyclerAdapter
import com.ktmmoe.shared.data.vos.Doctor

/**
 * Created by ktmmoe on 29, November, 2020
 **/
class RecentDoctorAdapter(private val delegate: RecentDoctorDelegate): BaseRecyclerAdapter<RecentDoctorViewHolder, Doctor>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentDoctorViewHolder =
        RecentDoctorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recent_doctor, parent, false), delegate)
}