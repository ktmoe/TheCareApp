package com.ktmmoe.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.mvp.presenters.PatientInfoPresenter
import com.ktmmoe.doctor.mvp.presenters.impls.PatientInfoPresenterImpl
import com.ktmmoe.doctor.mvp.views.PatientInfoView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.adapters.AnsweredQAndAAdapter
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.ConsultationRequest
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewpods.PatientInfoViewPod
import kotlinx.android.synthetic.main.activity_patient_info.*

class PatientInfoActivity : BaseActivity(), PatientInfoView {

    private lateinit var mPresenter: PatientInfoPresenter
    private lateinit var mAnsweredQAndAAdapter: AnsweredQAndAAdapter
    private lateinit var mPatientInfViewPod: PatientInfoViewPod

    companion object {
        fun intent(context: Context, consultationRequest: ConsultationRequest, fromSeeMore: Boolean = false) : Intent {
            val intent = Intent(context, PatientInfoActivity::class.java)
            intent.putExtra("consultation-request", consultationRequest)
            intent.putExtra("from-see-more", fromSeeMore)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)

        setupActionBar(toolbar = toolbar, text = getString(R.string.patient_info))
        setupPresenter()
        setupRecyclerView()
        setupListeners()
        mPresenter.onUiReady(this, this)
    }

    private fun setupListeners() {
        startConsultation.setOnClickListener {
            if (intent.getBooleanExtra("from-see-more",false)) finish()
            else mPresenter.onTapStartConsultation()
        }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<PatientInfoPresenterImpl, PatientInfoView>()
    }

    override fun getPassedExtra(): ConsultationRequest? = if (intent.hasExtra("consultation-request"))
        intent.getSerializableExtra("consultation-request") as ConsultationRequest
    else null

    override fun showPatientInfo(consultationRequest: ConsultationRequest) {
        ivPatient.load(consultationRequest.patient.image)
        mPatientInfViewPod = vpPatientInfo as PatientInfoViewPod
        mPatientInfViewPod.setData(patient = consultationRequest.patient)

        mAnsweredQAndAAdapter.setNewData(consultationRequest.patient.caseSummary)

    }

    private fun setupRecyclerView() {
        mAnsweredQAndAAdapter = AnsweredQAndAAdapter()
        rvAnsweredQAndA.adapter = mAnsweredQAndAAdapter
    }

    override fun navigateToConsultationScreen(consultationRequest: ConsultationRequest) {
        startActivity(ConsultationActivity.intent(this, consultationRecord = Consultation(id = consultationRequest.id, patient = consultationRequest.patient)))
    }
}