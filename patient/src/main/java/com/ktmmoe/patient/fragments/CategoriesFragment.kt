package com.ktmmoe.patient.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ktmmoe.patient.R
import com.ktmmoe.patient.activities.CompletePatientInfoActivity
import com.ktmmoe.patient.activities.ConsultationActivity
import com.ktmmoe.patient.adapters.AcceptedConsultationAdapter
import com.ktmmoe.patient.adapters.SpecialitiesAdapter
import com.ktmmoe.patient.adapters.RecentDoctorAdapter
import com.ktmmoe.patient.dialogs.ConsultationConfirmDialogFragment
import com.ktmmoe.patient.mvp.presenters.CategoriesPresenter
import com.ktmmoe.patient.mvp.presenters.impls.CategoriesPresenterImpl
import com.ktmmoe.patient.mvp.views.CategoriesView
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : BaseFragment(), CategoriesView {

    private lateinit var mPresenter: CategoriesPresenter
    private lateinit var mRecentDoctorAdapter: RecentDoctorAdapter
    private lateinit var mSpecialitiesAdapter: SpecialitiesAdapter
    private lateinit var mAcceptedRequestsAdapter: AcceptedConsultationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()
        setupRecyclers()
        mPresenter.onUiReady(requireContext(), viewLifecycleOwner)
    }

    private fun setupRecyclers() {
        mSpecialitiesAdapter = SpecialitiesAdapter(mPresenter)
        mRecentDoctorAdapter = RecentDoctorAdapter(mPresenter)
        mAcceptedRequestsAdapter = AcceptedConsultationAdapter(mPresenter)

        rvSpecialities.adapter = mSpecialitiesAdapter
        rvRecentConsultation.adapter = mRecentDoctorAdapter
        rvAcceptedRequests.adapter = mAcceptedRequestsAdapter
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<CategoriesPresenterImpl, CategoriesView>()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoriesFragment()
    }

    override fun showAcceptedConsultations(data: List<Consultation>) {
        mAcceptedRequestsAdapter.setNewData(data)
    }

    override fun showSpecialities(data: List<Speciality>) {
        mSpecialitiesAdapter.setNewData(data)
    }

    override fun showRecentConsultations(data: List<Doctor>) {
        view?.let {
            tvRecentConsultationTitle.visibility = View.VISIBLE
            rvRecentConsultation.visibility = View.VISIBLE
            mRecentDoctorAdapter.setNewData(data)
        }
    }

    override fun hideRecentConsultations() {
        view?.let {
            tvRecentConsultationTitle.visibility = View.GONE
            rvRecentConsultation.visibility = View.GONE
        }
    }

    override fun showConsultationConfirmDialog(speciality: Speciality) {
        ConsultationConfirmDialogFragment.newInstance(category = speciality.name, confirmCallback = {
            mPresenter.onTapConfirmConsultation(speciality)
        }).show(requireActivity().supportFragmentManager, CategoriesFragment::class.java.simpleName)
    }

    override fun navigateToCompletePatientInfo(speciality: Speciality, patient: Patient) {
        startActivity(CompletePatientInfoActivity.intent(requireContext(), patient, speciality))
    }

    override fun gotoConsultationScreen(consultation: Consultation) {
        startActivity(ConsultationActivity.intent(requireContext(), consultation))
    }
}