package com.ktmmoe.patient.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.ktmmoe.patient.R
import com.ktmmoe.patient.dialogs.CompletePatientInfoConfirmDialogFragment
import com.ktmmoe.patient.mvp.presenters.ConsultationPresenter
import com.ktmmoe.patient.mvp.presenters.impls.ConsultationPresenterImpl
import com.ktmmoe.patient.mvp.views.ConsultationView
import com.ktmmoe.shared.activities.BaseActivity
import com.ktmmoe.shared.adapters.AnsweredQAndAAdapter
import com.ktmmoe.shared.adapters.ChatMessageAdapter
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.utils.load
import com.ktmmoe.shared.viewpods.MessagingAreaViewPod
import com.ktmmoe.shared.viewpods.PatientInfoViewPod
import com.ktmmoe.shared.viewpods.PatientPrescriptChatMessageViewPod
import kotlinx.android.synthetic.main.activity_consultation.*

class ConsultationActivity : BaseActivity(), ConsultationView {
    private lateinit var mPresenter: ConsultationPresenter
    private lateinit var mPatientInfoViewPod: PatientInfoViewPod
    private lateinit var mAnsweredQAndAAdapter: AnsweredQAndAAdapter
    private lateinit var mChatMessageAdapter: ChatMessageAdapter
    private lateinit var mMessagingAreaViewPod: MessagingAreaViewPod
    private lateinit var mPatientPrescriptChatMessageViewPod: PatientPrescriptChatMessageViewPod

    companion object {
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

    private fun setupOnClickListeners() {
        tvSeeMore.setOnClickListener { mPresenter.onTapSeeMore() }
    }

    private fun setupViewPod() {
        mPatientInfoViewPod = vpPatientInfo as PatientInfoViewPod
        mMessagingAreaViewPod = vpMessagingArea as MessagingAreaViewPod
        mMessagingAreaViewPod.setDelegate(mPresenter)
        mMessagingAreaViewPod.hideEasyDoctorActions()
        mPatientPrescriptChatMessageViewPod = prescriptMessage as PatientPrescriptChatMessageViewPod
        mPatientPrescriptChatMessageViewPod.setDelegate(mPresenter)
    }

    private fun setupRecyclerView() {
        mAnsweredQAndAAdapter = AnsweredQAndAAdapter()
        rvAnsweredQAndA.adapter = mAnsweredQAndAAdapter

        mChatMessageAdapter = ChatMessageAdapter()
        rvChatMessages.adapter = mChatMessageAdapter
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<ConsultationPresenterImpl, ConsultationView>()
    }

    override fun getPassedExtra(): Consultation = intent.getSerializableExtra("consultation-record") as Consultation

    override fun showPatientInfoAndMedicalHistory(patient: Patient, caseSummary: List<CaseSummary>) {
        val cS = if (caseSummary.count() > 2) caseSummary.subList(0,2) else caseSummary
        mAnsweredQAndAAdapter.setNewData(cS)
        mPatientInfoViewPod.setData(patient = patient)
        setupActionBar(toolbar=toolbar, text = patient.name)
        ivPatient.load(patient.image)
    }

    override fun showFullPatientInfo(patient: Patient) {
        CompletePatientInfoConfirmDialogFragment.newInstance(patient, fromSeeMore = true).show(supportFragmentManager, ConsultationActivity::class.java.simpleName)
    }

    override fun showChatMessages(chats: List<ChatMessage>, mId: String) {
        mChatMessageAdapter.setChatRoomOwnerId(mId)
        mChatMessageAdapter.setNewData(chats)
    }

    override fun clearMessage() {
        mMessagingAreaViewPod.clearMessage()
    }

    override fun showPrescriptMedicineChatMessage(medicines: List<PrescriptMedicine>, image: String, date: String) {
        mPatientPrescriptChatMessageViewPod.visibility = View.VISIBLE
        mPatientPrescriptChatMessageViewPod.setData(medicines, image, date)
    }

    override fun hidePrescriptMedicineChatMessage() {
        mPatientPrescriptChatMessageViewPod.visibility = View.GONE
    }

    override fun disableChat() {
        mMessagingAreaViewPod.visibility = View.GONE
    }

}