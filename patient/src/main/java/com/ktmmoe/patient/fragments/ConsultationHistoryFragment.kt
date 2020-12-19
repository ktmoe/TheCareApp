package com.ktmmoe.patient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktmmoe.patient.R
import com.ktmmoe.patient.activities.ConsultationActivity
import com.ktmmoe.patient.adapters.ConsultationHistoryAdapter
import com.ktmmoe.patient.dialogs.CompletePatientInfoConfirmDialogFragment
import com.ktmmoe.patient.mvp.presenters.ConsultationHistoryPresenter
import com.ktmmoe.patient.mvp.presenters.impls.ConsultationHistoryPresenterImpl
import com.ktmmoe.patient.mvp.views.ConsultationHistoryView
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.dialogs.PrescriptionDialogFragment
import com.ktmmoe.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_consultation_history.*

class ConsultationHistoryFragment : BaseFragment(), ConsultationHistoryView {
    private lateinit var mPresenter: ConsultationHistoryPresenter

    private lateinit var mConsultationHistoryAdapter: ConsultationHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_consultation_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupRecyclerView()

        mPresenter.onUiReady(requireContext(), this)
    }

    private fun setupRecyclerView() {
        mConsultationHistoryAdapter = ConsultationHistoryAdapter(mPresenter)
        rvConsultationHistory.adapter = mConsultationHistoryAdapter
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<ConsultationHistoryPresenterImpl, ConsultationHistoryView>()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConsultationHistoryFragment()
    }

    override fun showChatHistories(list: List<Consultation>) {
        mConsultationHistoryAdapter.setNewData(list)
    }

    override fun showPatientMedicalRecord(patient: Patient) {
        CompletePatientInfoConfirmDialogFragment.newInstance(patient, fromSeeMore = true).show(requireFragmentManager(), ConsultationHistoryFragment::class.java.simpleName)
    }

    override fun showPrescriptionDialog(consultationRecord: Consultation, prescriptions: List<PrescriptMedicine>) {
        PrescriptionDialogFragment.newInstance(consultationRecord, prescriptions).show(requireFragmentManager(), ConsultationHistoryFragment::class.java.simpleName)
    }

    override fun navigateToConsultationScreen(consultation: Consultation) {
        requireActivity().startActivity(ConsultationActivity.intent(requireContext(), consultationRecord = consultation))
    }

    override fun showEmptyView() {
        emptyConsultationHistory.visibility = View.VISIBLE
        rvConsultationHistory.visibility = View.GONE
    }

    override fun hideEmptyView() {
        emptyConsultationHistory.visibility = View.GONE
        rvConsultationHistory.visibility = View.VISIBLE
    }
}