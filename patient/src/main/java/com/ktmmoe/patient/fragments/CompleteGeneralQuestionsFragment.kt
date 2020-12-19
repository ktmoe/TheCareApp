package com.ktmmoe.patient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktmmoe.patient.R
import com.ktmmoe.patient.activities.CompletePatientInfoActivity
import com.ktmmoe.patient.mvp.presenters.CompleteGeneralQuestionsPresenter
import com.ktmmoe.patient.mvp.presenters.impls.CompleteGeneralQuestionsPresenterImpl
import com.ktmmoe.patient.mvp.views.CompleteGeneralQuestionsView
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.fragments.BaseFragment
import com.ktmmoe.shared.viewpods.PatientInfoViewPod
import kotlinx.android.synthetic.main.fragment_complete_general_questions.*

private const val ARG_PARAM1 = "patient-info"
private const val ARG_PARAM2 = "speciality"

class CompleteGeneralQuestionsFragment : BaseFragment(), CompleteGeneralQuestionsView {
    private lateinit var mPatientInfoViewPod: PatientInfoViewPod
    private lateinit var mPresenter: CompleteGeneralQuestionsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_complete_general_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPod()
        setupPresenter()
        setupOnClickListeners()

        mPresenter.onUiReady(requireContext(), this)
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<CompleteGeneralQuestionsPresenterImpl, CompleteGeneralQuestionsView>()
    }

    private fun setupViewPod() {
        mPatientInfoViewPod = vpPatientInfo as PatientInfoViewPod
        mPatientInfoViewPod.hideWeightAndBloodPressure()
    }

    private fun setupOnClickListeners() {
        btnContinue.setOnClickListener {
            mPresenter.onTapNext(etWeight.text.toString(), etBloodPressure.text.toString()) {
                CompletePatientInfoActivity.currentAtStep2()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(patient: Patient?, speciality: Speciality?) =
                CompleteGeneralQuestionsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, patient)
                        putSerializable(ARG_PARAM2, speciality)
                    }
                }
    }

    override fun getPassedPatientInfo(): Patient =  arguments?.getSerializable(ARG_PARAM1) as Patient

    override fun getPassedSpeciality(): Speciality = arguments?.getSerializable(ARG_PARAM2) as Speciality

    override fun showSpecialQuestions(patient: Patient, speciality: Speciality) {
        requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.containerCompletePatientInfo, CompleteSpecialQuestionsFragment.newInstance(patient, speciality))
                .commit()
    }

    override fun showPatientInfo(patient: Patient) {
        mPatientInfoViewPod.setData(patient = patient)
    }
}