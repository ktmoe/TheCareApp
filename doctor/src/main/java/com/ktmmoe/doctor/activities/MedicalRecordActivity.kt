package com.ktmmoe.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.mvp.presenters.MedicalRecordPresenter
import com.ktmmoe.doctor.mvp.presenters.impls.MedicalRecordPresenterImpl
import com.ktmmoe.doctor.mvp.views.MedicalRecordView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.data.vos.Consultation
import kotlinx.android.synthetic.main.activity_medical_record.*

class MedicalRecordActivity : BaseActivity(), MedicalRecordView {

    private var mConsultationRecord: Consultation? = null
    private lateinit var mPresenter: MedicalRecordPresenter

    companion object {
        fun intent(context: Context, consultationRecord: Consultation, remark: String= ""): Intent {
            return Intent(context, MedicalRecordActivity::class.java).apply {
                putExtra("consultation-record", consultationRecord)
                putExtra("remark", remark)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_record)

        mConsultationRecord = intent.getSerializableExtra("consultation-record") as Consultation
        setupActionBar(toolbar, resources.getString(R.string.medical_record))
        setupPresenter()
        mPresenter.onUiReady(this, this)
        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        saveMedicalRecord.setOnClickListener { mPresenter.onTapSave(etRemark.text.toString()) }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<MedicalRecordPresenterImpl, MedicalRecordView>()
    }

    override fun getPassedConsultationRecord(): Consultation = mConsultationRecord ?: Consultation()
    override fun getPassedRemark(): String = intent.getStringExtra("remark") as String

    override fun showConsultationRecord(consultationRecord: Consultation) {
        tvPatientName.text = consultationRecord.patient.name
        tvDob.text = consultationRecord.patient.dob
        tvConsultedDate.text = consultationRecord.consultedDate

        if (getPassedRemark().isNotEmpty()) {
            saveMedicalRecord.visibility = View.GONE
            etRemark.setText(getPassedRemark())
        }
    }
}