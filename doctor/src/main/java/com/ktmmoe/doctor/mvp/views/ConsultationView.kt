package com.ktmmoe.doctor.mvp.views

import com.ktmmoe.shared.data.vos.*
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 01, December, 2020
 **/
interface ConsultationView: BaseView {
    fun getPassedExtra(): Consultation
    fun showPatientInfoAndMedicalHistory(patient: Patient, oneTimeCaseSummary: List<CaseSummary>)
    fun showFullPatientInfo(consultationRequest: ConsultationRequest)
    fun showChatMessages(chats:List<ChatMessage>, mId: String)
    fun clearMessage()
    fun navigateToPrescriptScreen(consultation: Consultation)
    fun navigateToQuestions()
    fun navigateToMedicalRecord(consultationRecord: Consultation)
    fun showPrescriptMedicineChatMessage(medicines: List<PrescriptMedicine>, image: String, date: String)
    fun hidePrescriptMedicineChatMessage()
    fun showConsultationEndDialog()
    fun disableChat()
}