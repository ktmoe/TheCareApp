package com.ktmmoe.patient.mvp.views

import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 01, December, 2020
 **/
interface ConsultationView: BaseView {
    fun getPassedExtra(): Consultation
    fun showPatientInfoAndMedicalHistory(patient: Patient, oneTimeCaseSummary: List<CaseSummary>)
    fun showFullPatientInfo(patient: Patient)
    fun showChatMessages(chats: List<ChatMessage>, mId: String)
    fun clearMessage()
    fun showPrescriptMedicineChatMessage(medicines: List<PrescriptMedicine>, image: String, date: String)
    fun hidePrescriptMedicineChatMessage()
    fun disableChat()
}