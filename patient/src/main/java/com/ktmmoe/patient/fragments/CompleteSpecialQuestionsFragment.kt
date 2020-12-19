package com.ktmmoe.patient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktmmoe.patient.R
import com.ktmmoe.patient.adapters.SpecialQAndAAdapter
import com.ktmmoe.patient.dialogs.CompletePatientInfoConfirmDialogFragment
import com.ktmmoe.patient.mvp.presenters.CompleteSpecialQuestionsPresenter
import com.ktmmoe.patient.mvp.presenters.impls.CompleteSpecialQuestionsPresenterImpl
import com.ktmmoe.patient.mvp.views.CompleteSpecialQuestionsView
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.SpecialQuestion
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_complete_special_questions.*

private const val ARG_PARAM1 = "patient-info"
private const val ARG_PARAM2 = "speciality"

class CompleteSpecialQuestionsFragment : BaseFragment(), CompleteSpecialQuestionsView {
    private lateinit var mSpecialQAndAAdapter: SpecialQAndAAdapter
    private lateinit var mPresenter: CompleteSpecialQuestionsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_complete_special_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupRecyclerView()
        mPresenter.onUiReady(requireContext(), this)
        setupOnClickListeners()
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<CompleteSpecialQuestionsPresenterImpl, CompleteSpecialQuestionsView>()
    }

    private fun setupRecyclerView() {
        mSpecialQAndAAdapter = SpecialQAndAAdapter(mPresenter)
        rvSpecialQAndA.adapter = mSpecialQAndAAdapter
    }

    private fun setupOnClickListeners() {
        btnMakeAppointment.setOnClickListener { mPresenter.onTapConfirmPatientInfo() }
    }

    companion object {
        @JvmStatic
        fun newInstance(patient: Patient?, speciality: Speciality?) =
            CompleteSpecialQuestionsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, patient)
                    putSerializable(ARG_PARAM2, speciality)
                }
            }
    }

    override fun getPassedPatientInfo(): Patient = arguments?.getSerializable(ARG_PARAM1) as Patient

    override fun getPassedSpeciality(): Speciality = arguments?.get(ARG_PARAM2) as Speciality

    override fun showSpecialQuestions(specialQuestions: List<SpecialQuestion>) {
        mSpecialQAndAAdapter.setNewData(specialQuestions)
    }

    override fun showConfirmPatientInfoDialog(patient: Patient) {
        CompletePatientInfoConfirmDialogFragment.newInstance(patient, callBack = {
            mPresenter.onConfirmConsultationRequest()
        }).show(requireFragmentManager(), CompleteSpecialQuestionsFragment::class.java.simpleName)
    }

    override fun navigateBackToHome() {
        requireActivity().finish()
    }
}