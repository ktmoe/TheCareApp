package com.ktmmoe.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.patient.R
import com.ktmmoe.patient.viewholders.SpecialQAndAViewHolder
import com.ktmmoe.patient.viewholders.SpecialQAndAViewHolderDelegate
import com.ktmmoe.shared.adapters.BaseRecyclerAdapter
import com.ktmmoe.shared.data.vos.SpecialQuestion

/**
 * Created by ktmmoe on 10, December, 2020
 **/
class SpecialQAndAAdapter(private val mDelegateSpecial: SpecialQAndAViewHolderDelegate): BaseRecyclerAdapter<SpecialQAndAViewHolder, SpecialQuestion>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialQAndAViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_q_and_a, parent, false)
        return SpecialQAndAViewHolder(view, mDelegateSpecial)
    }
}