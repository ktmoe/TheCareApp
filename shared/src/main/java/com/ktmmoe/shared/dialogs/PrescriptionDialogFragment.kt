package com.ktmmoe.shared.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ktmmoe.shared.R
import com.ktmmoe.shared.adapters.PrescriptionAdapter
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import kotlinx.android.synthetic.main.dialog_fragment_prescription.*

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class PrescriptionDialogFragment: DialogFragment() {

    override fun getTheme(): Int = R.style.RoundedCornersDialog

    companion object {
        var consultation: Consultation? = null
        var prescriptions: List<PrescriptMedicine> = listOf()
        fun newInstance(consultation: Consultation, prescriptions: List<PrescriptMedicine>): PrescriptionDialogFragment {
            Companion.consultation = consultation
            Companion.prescriptions = prescriptions
            return PrescriptionDialogFragment()
        }
    }

    private lateinit var mPrescriptionAdapter: PrescriptionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_prescription, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvPatientName.text = consultation?.patient?.name
        tvConsultedDate.text = consultation?.consultedDate
        mPrescriptionAdapter = PrescriptionAdapter()
        rvPrescription.adapter = mPrescriptionAdapter
        mPrescriptionAdapter.setNewData(prescriptions)

        btnClose.setOnClickListener { dismiss() }
    }
}