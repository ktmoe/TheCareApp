package com.ktmmoe.doctor.viewholders

import android.view.View
import com.ktmmoe.doctor.R
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_prescript_medicine.view.*

/**
 * Created by ktmmoe on 02, December, 2020
 **/
class PrescriptMedicineViewHolder(private val mDelegate: PrescriptMedicineViewHolderDelegate, private val itemView: View): BaseViewHolder<PrescriptMedicine>(itemView) {

    override fun bindData(data: PrescriptMedicine) {
        itemView.medicineName.text = data.medicine.name
        if (data.routine.tab != 0) itemView.ivAddRemove.setImageResource(R.drawable.ic_remove)

        itemView.setOnClickListener {
            mDelegate.onTapPrescriptMedicine(data)
        }
    }
}

interface PrescriptMedicineViewHolderDelegate {
    fun onTapPrescriptMedicine(medicine: PrescriptMedicine)
}