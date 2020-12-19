package com.ktmmoe.doctor.mvp.presenters.impls

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.doctor.mvp.presenters.ConsultationPresenter
import com.ktmmoe.doctor.mvp.views.ConsultationView
import com.ktmmoe.shared.data.models.DoctorModel
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl
import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ktmmoe on 01, December, 2020
 **/
@SuppressLint("SimpleDateFormat")
class ConsultationPresenterImpl: AbstractBasePresenter<ConsultationView>(), ConsultationPresenter {
    private val mDoctorModel: DoctorModel = DoctorModelImpl
    private var mDoctor : Doctor = Doctor()
    private var mConsultation: Consultation = Consultation()
    private var chatMessageCount = 0

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mConsultation = mView.getPassedExtra()
        mDoctor = mDoctorModel.getPersistedDoctor().first()

        mDoctorModel.observeConsultationChatMessages(mConsultation.id, {list->
            chatMessageCount = list.count()
            mView.showChatMessages(list.sortedBy { it.id }, mConsultation.doctorId)
        }, {
            mView.showSnackBar(it)
        })

        mView.showPatientInfoAndMedicalHistory(mConsultation.patient, mConsultation.patient.caseSummary)
        checkIfConsultationIsEnded(mConsultation)
    }

    private fun checkIfConsultationIsEnded (consultation: Consultation) {
        if (consultation.consultedDate.isNotEmpty()) {
            getPrescriptMedicine(consultation.id)
            mView.disableChat()
        }
    }

    override fun onTapClip() {
        mView.showSnackBar("")
    }

    override fun onTapSend(message: String) {
        if (message.isNotEmpty()) {
            mView.clearMessage()
            sendMessageToConversation(message)
        }
    }

    override fun onTapQuestions() {
        mView.navigateToQuestions()
    }

    override fun onTapPrescript() {
        mView.navigateToPrescriptScreen(mConsultation.copy(specialtyId = mDoctor.specialtyId))
    }

    override fun onTapMedicalRecord() {
        mView.navigateToMedicalRecord(mConsultation)
    }

    override fun onSpecialQuestionActivityResultOk(question: String) {
        sendMessageToConversation(question)
    }

    private fun sendMessageToConversation (message: String) {
        val chatMessage = ChatMessage(
                id = "$chatMessageCount",
                sentAt = SimpleDateFormat("hh:mm a").format(Date()),
                sentBy = SentBy(
                        senderId = mConsultation.doctorId,
                        name = mConsultation.doctor.name,
                        image = mConsultation.doctor.image),
                textMessage = message
        )
        mDoctorModel.sendMessageToConversation(mConsultation, chatMessage, {}, {
            mView.showSnackBar(it)
        })
    }

    override fun onPrescriptActivityResultOk() {
        getPrescriptMedicine(mConsultation.id)
    }

    private fun getPrescriptMedicine(consultationId: String) {
        mDoctorModel.getPrescriptionsByConsultationId(consultationId, {medicines->
            mView.showPrescriptMedicineChatMessage(medicines, mConsultation.doctor.image, mConsultation.consultedDate)
            mView.showConsultationEndDialog()
        }, {failure->
            mView.showSnackBar(failure)
        })
    }

    override fun onTapSeeMore() {
        mView.showFullPatientInfo(mConsultation.getRequest())
    }
}