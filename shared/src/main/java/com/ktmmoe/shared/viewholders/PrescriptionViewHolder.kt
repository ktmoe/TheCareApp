package com.ktmmoe.shared.viewholders

import android.view.View
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.data.vos.Prescription
import com.ktmmoe.shared.data.vos.Routine
import kotlinx.android.synthetic.main.item_prescription.view.*

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class PrescriptionViewHolder(private val itemView: View): BaseViewHolder<PrescriptMedicine>(itemView) {

    override fun bindData(data: PrescriptMedicine) {
        itemView.medicineName.text = data.medicine.name
//        itemView.tvAmount.text = data.amount
        itemView.tvDosage.text = data.routine.tab.toString()
        itemView.tvDuration.text = data.routine.duration
        itemView.tvTime.text = getTime(data.routine)
//        itemView.tvRepeat.text = data.repeat
        itemView.tvRemark.text = data.routine.remark
    }

    private fun getTime(routine: Routine) : String {
        var time = ""
        if (routine.morning) time += "Morning/"
        if (routine.noon) time += "Noon/"
        if (routine.night) time += "Evening/"
        return time.substring(0, time.length)
    }
}