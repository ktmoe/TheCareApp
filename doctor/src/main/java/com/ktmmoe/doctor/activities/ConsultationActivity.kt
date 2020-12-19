package com.ktmmoe.doctor.activities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.ktmmoe.doctor.R
import com.ktmmoe.doctor.mvp.presenters.ConsultationPresenter
import com.ktmmoe.doctor.mvp.presenters.impls.ConsultationPresenterImpl
import com.ktmmoe.doctor.mvp.views.ConsultationView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.adapters.AnsweredQAndAAdapter
import com.ktmmoe.shared.adapters.ChatMessageAdapter
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewpods.DoctorPrescriptChatMessageViewPod
import com.ktmmoe.shared.viewpods.MessagingAreaViewPod
import com.ktmmoe.shared.viewpods.PatientInfoViewPod
import kotlinx.android.synthetic.main.activity_consultation.*


class ConsultationActivity : BaseActivity(), ConsultationView {
    private lateinit var mPresenter: ConsultationPresenter
    private lateinit var mPatientInfoViewPod: PatientInfoViewPod
    private lateinit var mAnsweredQAndAAdapter: AnsweredQAndAAdapter
    private lateinit var mChatMessageAdapter: ChatMessageAdapter
    private lateinit var mMessagingAreaViewPod: MessagingAreaViewPod
    private lateinit var mDoctorPrescriptChatMessageViewPod: DoctorPrescriptChatMessageViewPod

    companion object {
        const val prescriptScreenRequestCode = 111
        const val questionScreenRequestCode = 1
        fun intent(context: Context, consultationRecord: Consultation): Intent {
            val intent = Intent(context, ConsultationActivity::class.java)
            return intent.putExtra("consultation-record", consultationRecord)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultation)

        setupPresenter()
        setupViewPod()
        setupRecyclerView()
        setupOnClickListeners()
        mPresenter.onUiReady(this, this)
    }

    private fun setupViewPod() {
        mPatientInfoViewPod = vpPatientInfo as PatientInfoViewPod
        mMessagingAreaViewPod = vpMessagingArea as MessagingAreaViewPod
        mMessagingAreaViewPod.setDelegate(mPresenter)
        mMessagingAreaViewPod.setEasyDoctorActionsDelegate(mPresenter)
        mDoctorPrescriptChatMessageViewPod = prescriptMessage as DoctorPrescriptChatMessageViewPod
    }

    private fun setupRecyclerView() {
        mAnsweredQAndAAdapter = AnsweredQAndAAdapter()
        rvAnsweredQAndA.adapter = mAnsweredQAndAAdapter

        mChatMessageAdapter = ChatMessageAdapter()
        rvChatMessages.adapter = mChatMessageAdapter
    }

    private fun setupOnClickListeners() {
        tvSeeMore.setOnClickListener { mPresenter.onTapSeeMore() }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<ConsultationPresenterImpl, ConsultationView>()
    }

    override fun getPassedExtra(): Consultation = intent.getSerializableExtra("consultation-record") as Consultation

    override fun showPatientInfoAndMedicalHistory(patient: Patient, oneTimeCaseSummary: List<CaseSummary>) {
        val cS = if (oneTimeCaseSummary.count() > 2) oneTimeCaseSummary.subList(0,2) else oneTimeCaseSummary
        mAnsweredQAndAAdapter.setNewData(cS)
        mPatientInfoViewPod.setData(patient = patient)
        setupActionBar(toolbar=toolbar, text = patient.name)
        ivPatient.load(patient.image)
    }

    override fun showFullPatientInfo(consultationRequest: ConsultationRequest) {
        startActivity(PatientInfoActivity.intent(this, consultationRequest, true))
    }

    override fun showChatMessages(chats: List<ChatMessage>, mId: String) {
        mChatMessageAdapter.setChatRoomOwnerId(mId)
        mChatMessageAdapter.setNewData(chats)
    }

    override fun clearMessage() {
        mMessagingAreaViewPod.clearMessage()
    }

    override fun navigateToPrescriptScreen(consultation: Consultation) {
        Log.d("MEDICINES", "Consultation >> ${consultation.specialtyId}")
        startActivityForResult(PrescriptActivity.intent(this, consultation), prescriptScreenRequestCode)
    }

    override fun navigateToQuestions() {
        startActivityForResult(EasyQuestionsActivity.intent(this), questionScreenRequestCode)
    }

    override fun navigateToMedicalRecord(consultationRecord: Consultation) {
        startActivity(MedicalRecordActivity.intent(this, consultationRecord))
    }

    override fun showPrescriptMedicineChatMessage(medicines: List<PrescriptMedicine>, image: String, date: String) {
        mDoctorPrescriptChatMessageViewPod.visibility = View.VISIBLE
        mDoctorPrescriptChatMessageViewPod.setData(medicines, image, date)
    }

    override fun hidePrescriptMedicineChatMessage() {
        mDoctorPrescriptChatMessageViewPod.visibility = View.GONE
    }

    override fun showConsultationEndDialog() {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            this.setMessage("This consultation has ended.")
            this.setNeutralButton("OK") { _, _ ->
                finish()
            }
        }
        val alert = builder.create()
        alert.apply {
            show()
            getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(Color.BLUE)
        }
    }

    override fun disableChat() {
        mMessagingAreaViewPod.visibility = View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == questionScreenRequestCode) {
            val question = data?.getSerializableExtra("question") as String
            question.let { mPresenter.onSpecialQuestionActivityResultOk(it) }
        }
        else if (resultCode == RESULT_OK && requestCode == prescriptScreenRequestCode) {
            mPresenter.onPrescriptActivityResultOk()
        }
    }
}