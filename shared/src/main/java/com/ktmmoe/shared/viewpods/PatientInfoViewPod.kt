package com.ktmmoe.shared.viewpods

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.ktmmoe.shared.data.vos.Patient
import kotlinx.android.synthetic.main.viewpod_patient_info.view.*

/**
 * Created by ktmmoe on 30, November, 2020
 **/
class PatientInfoViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    fun setData(consultedDate: String = "", patient: Patient) {
        if (consultedDate.isEmpty()) hideConsultedDate()
        else tvConsultedDate.text = consultedDate

        tvPatientName.text = patient.name
        tvDob.text = patient.dob
        tvHeight.text = patient.height
        tvBloodType.text = patient.bloodType
        tvAllergicPrescriptions.text = patient.allergicPrescriptions
        tvWeight.text = patient.weight
        tvBloodPressure.text = patient.bloodPressure
    }

    fun hideWeightAndBloodPressure() {
        weightTitle.visibility = View.GONE
        weightColon.visibility = View.GONE
        tvWeight.visibility = View.GONE
        bloodPressureTitle.visibility = View.GONE
        bloodPressureColon.visibility = View.GONE
        tvBloodPressure.visibility = View.GONE
    }

    private fun hideConsultedDate() {
        consultedDateTitle.visibility = View.GONE
        consultedDateColon.visibility = View.GONE
        tvConsultedDate.visibility = View.GONE
    }

    fun onlyOneTimeInfo() {
        patientNameTitle.visibility = View.GONE
        patientNameColon.visibility = View.GONE
        tvPatientName.visibility = View.GONE
        weightTitle.visibility = View.GONE
        weightColon.visibility = View.GONE
        tvWeight.visibility = View.GONE
        bloodPressureTitle.visibility = View.GONE
        bloodPressureColon.visibility = View.GONE
        tvBloodPressure.visibility = View.GONE
    }
}