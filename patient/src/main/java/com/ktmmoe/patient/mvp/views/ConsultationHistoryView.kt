package com.ktmmoe.patient.mvp.views

import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 13, December, 2020
 **/
interface ConsultationHistoryView: BaseView {
    fun showChatHistories(list: List<Consultation>)
    fun showPatientMedicalRecord(patient: Patient)
    fun showPrescriptionDialog(consultationRecord: Consultation, prescriptions: List<PrescriptMedicine>)
    fun navigateToConsultationScreen(consultation: Consultation)
    fun showEmptyView()
    fun hideEmptyView()
}