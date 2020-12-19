package com.ktmmoe.patient.viewholders

import android.view.View
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_speciality.view.*

/**
 * Created by ktmmoe on 29, November, 2020
 **/
class SpecialityViewHolder(itemView: View, private val delegate: SpecialitiesViewHolderDelegate): BaseViewHolder<Speciality>(itemView) {

    override fun bindData(data: Speciality) {
        itemView.ivSpeciality.load(data.image)

        itemView.tvSpecialityName.text = data.name

        itemView.setOnClickListener {
            delegate.onTapSpeciality(data)
        }
    }
}

interface SpecialitiesViewHolderDelegate{
    fun onTapSpeciality(data: Speciality)
}