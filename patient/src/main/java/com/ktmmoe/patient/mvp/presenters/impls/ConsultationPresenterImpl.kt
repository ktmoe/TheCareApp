package com.ktmmoe.patient.mvp.presenters.impls

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.ConsultationPresenter
import com.ktmmoe.patient.mvp.views.ConsultationView
import com.ktmmoe.shared.data.models.PatientModel
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.data.vos.ChatMessage
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.SentBy
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ktmmoe on 01, December, 2020
 **/
class ConsultationPresenterImpl: AbstractBasePresenter<ConsultationView>(), ConsultationPresenter {
    private val mPatientModel: PatientModel = PatientModelImpl
    private var consultationRecord: Consultation? = null
    private var chatMessageCount = 0

    override fun onTapSeeMore() {
        consultationRecord?.let {
            mView.showFullPatientInfo(it.patient)
        }
    }

    override fun onTapCheckout() {
        mView.showSnackBar("")
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        consultationRecord = mView.getPassedExtra()
        consultationRecord?.let {consultation->
            mView.showPatientInfoAndMedicalHistory(consultation.patient, consultation.patient.caseSummary)
            mPatientModel.observeConsultationChatMessages(consultation.id, { list ->
                chatMessageCount = list.count()
                mView.showChatMessages(list.sortedBy { it.id }, consultation.patientId)
            }, {failure->
                mView.showSnackBar(failure)
            })

            mPatientModel.observePrescriptionsByConsultationId(consultation.id, {medicines->
                if (medicines.isNotEmpty()) mView.showPrescriptMedicineChatMessage(medicines, consultation.doctor.image, consultation.consultedDate)
                else mView.hidePrescriptMedicineChatMessage()
            }, { failure->
                mView.showSnackBar(failure)
            })
            checkIfConsultationIsEnded(consultation)
        }
    }

    private fun checkIfConsultationIsEnded(consultation: Consultation) {
        if (consultation.consultedDate.isNotEmpty()) {
            mView.disableChat()
        }
    }

    override fun onTapClip() {
        mView.showSnackBar("")
    }

    @SuppressLint("SimpleDateFormat")
    override fun onTapSend(message: String) {
        if (message.isNotEmpty()) {
            mView.clearMessage()
            consultationRecord?.let {
                val chatMessage = ChatMessage(
                        id = "$chatMessageCount",
                        sentAt = SimpleDateFormat("hh:mm a").format(Date()),
                        sentBy = SentBy(
                                senderId =it.patientId,
                                name = it.patient.name,
                                image = it.patient.image),
                        textMessage = message
                )
                mPatientModel.sendMessageToConversation(it, chatMessage, {}, {
                    mView.showSnackBar(it)
                })
            }
        }
    }
}