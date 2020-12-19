package com.ktmmoe.doctor.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ktmmoe.doctor.R
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.data.vos.Routine
import com.ktmmoe.shared.utils.showSnackBar
import kotlinx.android.synthetic.main.dialog_fragment_prescript_medicine.*

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class PrescriptMedicineDialogFragment: DialogFragment() {

    override fun getTheme(): Int = R.style.RoundedCornersDialog

    companion object {
        private var prescriptMedicine: PrescriptMedicine? = null
        private var addMedicineCallBack: (PrescriptMedicine) -> Unit = {}
        fun newInstance(prescriptMedicine: PrescriptMedicine, addMedicineCallBack: (PrescriptMedicine) -> Unit): PrescriptMedicineDialogFragment {
            this.prescriptMedicine = prescriptMedicine
            this.addMedicineCallBack = addMedicineCallBack
            return PrescriptMedicineDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_prescript_medicine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medicineName.text = prescriptMedicine?.medicine?.name

        addMedicine.setOnClickListener {

            prescriptMedicine?.let {
                if (validateInputs()) {
                    dismiss()
                    addMedicineCallBack.invoke(getPrescriptMedicineWithRoutine(it))
                }
                else requireView().showSnackBar("Please fill in all fields.")
            }
        }
    }

    private fun validateInputs() :Boolean = !(etTabCount.text.isNullOrEmpty() || etDaysToTake.text.isNullOrEmpty())

    private fun getPrescriptMedicineWithRoutine(prescriptMedicine: PrescriptMedicine) : PrescriptMedicine {
        val tabs = if (etTabCount.text.isNullOrEmpty()) 0 else etTabCount.text.toString().toInt()
        return prescriptMedicine.copy(
                routine = Routine(
                        tab = tabs,
                        duration = etDaysToTake.text.toString(),
                        morning = morningChip.isChecked,
                        noon = noonChip.isChecked,
                        night = nightChip.isChecked,
                        beforeMeal = beforeMealChip.isChecked,
                        afterMeal = afterMealChip.isChecked,
                        remark = etRemark.text.toString()
                )
        )
    }
}