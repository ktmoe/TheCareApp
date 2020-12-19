package com.ktmmoe.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.patient.R
import com.ktmmoe.patient.viewholders.SpecialitiesViewHolderDelegate
import com.ktmmoe.patient.viewholders.SpecialityViewHolder
import com.ktmmoe.shared.adapters.BaseRecyclerAdapter
import com.ktmmoe.shared.data.vos.Speciality

/**
 * Created by ktmmoe on 29, November, 2020
 **/
class SpecialitiesAdapter(private val delegate: SpecialitiesViewHolderDelegate): BaseRecyclerAdapter<SpecialityViewHolder, Speciality>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityViewHolder =
        SpecialityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_speciality, parent, false), delegate)
}