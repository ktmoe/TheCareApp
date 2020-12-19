package com.ktmmoe.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.viewholders.PrescriptMedicineViewHolder
import com.ktmmoe.doctor.viewholders.PrescriptMedicineViewHolderDelegate
import com.ktmmoe.shared.adapters.BaseRecyclerAdapter
import com.ktmmoe.shared.data.vos.PrescriptMedicine

/**
 * Created by ktmmoe on 02, December, 2020
 **/
class PrescriptMedicineAdapter(private val mDelegate: PrescriptMedicineViewHolderDelegate): BaseRecyclerAdapter<PrescriptMedicineViewHolder, PrescriptMedicine>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptMedicineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prescript_medicine, parent, false)
        return PrescriptMedicineViewHolder(mDelegate, view)
    }
}