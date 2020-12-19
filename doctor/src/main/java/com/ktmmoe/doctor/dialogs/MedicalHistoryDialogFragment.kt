package com.ktmmoe.doctor.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ktmmoe.doctor.R
import com.ktmmoe.shared.adapters.AnsweredQAndAAdapter
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.viewpods.PatientInfoViewPod
import kotlinx.android.synthetic.main.dialog_fragment_medical_history.*

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class MedicalHistoryDialogFragment: DialogFragment() {

    override fun getTheme(): Int = R.style.RoundedCornersDialog
    private lateinit var mPatientInfoViewPod: PatientInfoViewPod
    private lateinit var mAnsweredQAndAAdapter: AnsweredQAndAAdapter

    companion object {
        var consultationRecord: Consultation? = null
        fun newInstance(consultationRecord: Consultation): MedicalHistoryDialogFragment {
            Companion.consultationRecord = consultationRecord
            return MedicalHistoryDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_medical_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAnsweredQAndAAdapter = AnsweredQAndAAdapter()
        rvAnsweredQAndA.adapter = mAnsweredQAndAAdapter
        mAnsweredQAndAAdapter.setNewData(consultationRecord?.patient?.caseSummary ?: emptyList())

        mPatientInfoViewPod = vpPatientInfo as PatientInfoViewPod
        mPatientInfoViewPod.setData(consultedDate = consultationRecord?.consultedDate ?: "", patient = consultationRecord?.patient ?: Patient())

        btnClose.setOnClickListener { dismiss() }
    }
}