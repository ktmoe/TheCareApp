package com.ktmmoe.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.adapters.ConsultationRecordAdapter
import com.ktmmoe.doctor.adapters.ConsultationRequestAdapter
import com.ktmmoe.doctor.dialogs.MedicalHistoryDialogFragment
import com.ktmmoe.shared.dialogs.PrescriptionDialogFragment
import com.ktmmoe.doctor.dialogs.SelectTimeDialogFragment
import com.ktmmoe.doctor.mvp.presenters.MainPresenter
import com.ktmmoe.doctor.mvp.presenters.impls.MainPresenterImpl
import com.ktmmoe.doctor.mvp.views.MainView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.data.vos.ConsultationRequest
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.utils.load
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {
    private lateinit var mPresenter: MainPresenter
    private lateinit var mConsultationRequestAdapter: ConsultationRequestAdapter
    private lateinit var mConsultationRecordAdapter: ConsultationRecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPresenter()
        setupRecycler()
        mPresenter.onUiReady(this, this)
        setupListeners()
    }

    private fun setupListeners() {
        ivDoctorProfile.setOnClickListener { mPresenter.onTapProfile() }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<MainPresenterImpl, MainView>()
    }

    private fun setupRecycler() {
        mConsultationRecordAdapter = ConsultationRecordAdapter(mPresenter)
        rvConsultationRecord.adapter = mConsultationRecordAdapter

        mConsultationRequestAdapter = ConsultationRequestAdapter(mPresenter, mPresenter)
        rvConsultationRequest.adapter = mConsultationRequestAdapter
    }

    override fun bindDoctorInfo(doctor: Doctor) {
        ivDoctorProfile.load(doctor.image)
        tvDoctorName.text = doctor.name
    }

    override fun hideNewConsultationRequest() {
    }

    override fun hideOldConsultationRequest() {
    }

    override fun showConsultationRequests(consultationRequests: List<ConsultationRequest>) {
        mConsultationRequestAdapter.setNewData(consultationRequests)
    }

    override fun showConsultationRecord(data: List<Consultation>) {
        rvConsultationRecord.visibility = View.VISIBLE
        mConsultationRecordAdapter.setNewData(data)
    }

    override fun showEmptyScheduleRecord() {
        emptyConsultationRecord.visibility = View.VISIBLE
        rvConsultationRecord.visibility = View.GONE
    }

    override fun hideEmptyScheduleRecord() {
        emptyConsultationRecord.visibility = View.GONE
        rvConsultationRecord.visibility = View.VISIBLE
    }

    override fun showSelectTimeDialog() {
        SelectTimeDialogFragment.newInstance{ h, m->
            val time = if (h < 12) "$h : $m AM"
            else "${h - 12} : $m PM"
            showSnackBar(time)
        }.show(supportFragmentManager, MainActivity::class.java.simpleName)
    }

    override fun navigateToPatientInfo(consultationRequest: ConsultationRequest) {
        startActivity(PatientInfoActivity.intent(this, consultationRequest))
    }

    override fun showPrescriptionDialog(consultationRecord: Consultation, prescriptions: List<PrescriptMedicine>) {
        PrescriptionDialogFragment.newInstance(consultationRecord, prescriptions).show(supportFragmentManager, MainActivity::class.java.simpleName)
    }

    override fun showMedicalHistoryDialog(consultationRecord: Consultation) {
        MedicalHistoryDialogFragment.newInstance(consultationRecord).show(supportFragmentManager, MainActivity::class.java.simpleName)
    }

    override fun navigateToConsultationScreen(consultationRecord: Consultation) {
        startActivity(ConsultationActivity.intent(this, consultationRecord = consultationRecord))
    }

    override fun navigateToProfileScreen(doctor: Doctor) {
        startActivity(ProfileActivity.intent(this, doctor))
    }

    override fun navigateToMedicalRecordScreen(consultationRecord: Consultation, remark: String) {
        startActivity(MedicalRecordActivity.intent(this, consultationRecord, remark))
    }

    companion object {
        fun intent(context: Context) : Intent = Intent(context, MainActivity::class.java)
    }

}