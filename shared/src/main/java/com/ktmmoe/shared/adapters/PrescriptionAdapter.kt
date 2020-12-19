package com.ktmmoe.shared.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.shared.R
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.data.vos.Prescription
import com.ktmmoe.shared.viewholders.PrescriptionViewHolder

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class PrescriptionAdapter: BaseRecyclerAdapter<PrescriptionViewHolder, PrescriptMedicine>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionViewHolder =
            PrescriptionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_prescription, parent, false))
}