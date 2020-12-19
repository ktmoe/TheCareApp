package com.ktmmoe.patient.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.ktmmoe.patient.R
import com.ktmmoe.shared.adapters.AnsweredQAndAAdapter
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.utils.consultationConfirmImage
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewpods.PatientInfoViewPod
import kotlinx.android.synthetic.main.dialog_fragment_complete_patient_info_confirm.*

/**
 * Created by ktmmoe on 10, December, 2020
 **/
class CompletePatientInfoConfirmDialogFragment: DialogFragment() {

    override fun getTheme(): Int = R.style.RoundedCornersDialog
    private lateinit var mPatientInfoViewPod: PatientInfoViewPod
    private lateinit var mAnsweredQAndAAdapter: AnsweredQAndAAdapter

    companion object {
        var mPatient : Patient? = null
        var mCallBack: ()->Unit = {}
        var fromSeeMore: Boolean = false
        fun newInstance(patient: Patient, callBack: ()-> Unit = {}, fromSeeMore: Boolean = false): CompletePatientInfoConfirmDialogFragment {
            mPatient = patient
            mCallBack = callBack
            this.fromSeeMore = fromSeeMore
            return CompletePatientInfoConfirmDialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_complete_patient_info_confirm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivPatientInfoConfirm.load(consultationConfirmImage)

        mAnsweredQAndAAdapter = AnsweredQAndAAdapter()
        rvAnsweredQAndA.adapter = mAnsweredQAndAAdapter
        mAnsweredQAndAAdapter.setNewData(mPatient?.caseSummary ?: emptyList())

        mPatientInfoViewPod = vpPatientInfo as PatientInfoViewPod
        mPatientInfoViewPod.setData(patient = mPatient ?: Patient())

        if (fromSeeMore) btnMakeAppointment.visibility = View.GONE

        btnMakeAppointment.setOnClickListener {
            dismiss()
            mCallBack.invoke()
        }
    }
}