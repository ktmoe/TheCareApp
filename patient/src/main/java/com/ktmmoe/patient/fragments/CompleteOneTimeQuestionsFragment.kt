package com.ktmmoe.patient.fragments

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import com.ktmmoe.patient.R
import com.ktmmoe.patient.activities.CompletePatientInfoActivity
import com.ktmmoe.patient.mvp.presenters.CompleteOneTimeQuestionsPresenter
import com.ktmmoe.patient.mvp.presenters.impls.CompleteOneTimeQuestionsPresenterImpl
import com.ktmmoe.patient.mvp.views.CompleteOneTimeQuestionsView
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_complete_one_time_questions.*
import java.util.*

private const val ARG_PARAM1 = "patient-info"
private const val ARG_PARAM2 = "speciality"

class CompleteOneTimeQuestionsFragment : BaseFragment(), CompleteOneTimeQuestionsView {
    private lateinit var mPresenter: CompleteOneTimeQuestionsPresenter
    private lateinit var mSpinnerArrayAdapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_complete_one_time_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()
        setupOnClickListeners()
        setupSpinner()
        mPresenter.onUiReady(requireContext(), this)
    }

    private fun setupOnClickListeners() {
        dateOfDob.setOnClickListener { mPresenter.onTapDOB() }
        monthOfDob.setOnClickListener { mPresenter.onTapDOB() }
        yearOfDob.setOnClickListener { mPresenter.onTapDOB() }
        btnContinue.setOnClickListener {
            mPresenter.onTapNext(height = etHeight.text.toString(), bloodType = spinnerBloodType.selectedItem.toString(), weight = etWeight.text.toString(), bloodPressure = etBloodPressure.text.toString(), allergic = etAllergicPrescriptions.text.toString(),
                    callback = { CompletePatientInfoActivity.currentAtStep2() }) }
    }

    private fun setupSpinner() {
        mSpinnerArrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, requireContext().resources.getStringArray(R.array.blood_types))
        mSpinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBloodType.adapter = mSpinnerArrayAdapter
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<CompleteOneTimeQuestionsPresenterImpl, CompleteOneTimeQuestionsView>()
    }

    override fun getPassedPatientInfo(): Patient = arguments?.getSerializable(ARG_PARAM1) as Patient

    override fun getPassedSpeciality(): Speciality = arguments?.getSerializable(ARG_PARAM2) as Speciality

    override fun showSpecialQuestions(patient: Patient, speciality: Speciality) {
        requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.containerCompletePatientInfo, CompleteSpecialQuestionsFragment.newInstance(patient, speciality))
                .commit()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun showDatePickerDialog(onPicked: (Int, Int, Int) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(requireContext(), R.style.MyDatePickerDialogTheme, { _, y, m, d ->
            onPicked(d, m+1, y)
        }, year, month, day).show()
    }

    override fun showPickedDOB(day: Int, month: Int, year: Int) {
        dateOfDob.text = "$day"
        monthOfDob.text = "$month"
        yearOfDob.text = "$year"
    }

    companion object {
        @JvmStatic
        fun newInstance(patient: Patient?, speciality: Speciality?) =
                CompleteOneTimeQuestionsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, patient)
                        putSerializable(ARG_PARAM2, speciality)
                    }
                }
    }
}