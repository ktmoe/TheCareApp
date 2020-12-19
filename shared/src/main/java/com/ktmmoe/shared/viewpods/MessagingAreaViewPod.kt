package com.ktmmoe.shared.viewpods

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.viewpod_messaging_area.view.*

/**
 * Created by ktmmoe on 01, December, 2020
 **/
class MessagingAreaViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var mDelegate: MessagingAreaViewPodDelegate? = null
    private var mEasyDoctorActionsDelegate: EasyDoctorActionsDelegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setupListeners()
    }

    private fun setupListeners() {
        clip.setOnClickListener { mDelegate?.onTapClip() }
        send.setOnClickListener { mDelegate?.onTapSend(etMessage.text.toString()) }

        questions.setOnClickListener { mEasyDoctorActionsDelegate?.onTapQuestions() }
        prescript.setOnClickListener { mEasyDoctorActionsDelegate?.onTapPrescript() }
        medicalRecord.setOnClickListener { mEasyDoctorActionsDelegate?.onTapMedicalRecord() }
    }

    fun hideEasyDoctorActions() {
        questions.visibility = View.GONE
        prescript.visibility = View.GONE
        medicalRecord.visibility = View.GONE
    }

    fun setDelegate(delegate: MessagingAreaViewPodDelegate) {
        mDelegate = delegate
    }

    fun setEasyDoctorActionsDelegate(doctorActionsDelegate: EasyDoctorActionsDelegate) {
        mEasyDoctorActionsDelegate = doctorActionsDelegate
    }

    fun clearMessage() {
        etMessage.setText("")
    }
}

interface MessagingAreaViewPodDelegate {
    fun onTapClip()
    fun onTapSend(message: String)
}

interface EasyDoctorActionsDelegate {
    fun onTapQuestions()
    fun onTapPrescript()
    fun onTapMedicalRecord()
}