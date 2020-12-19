package com.ktmmoe.doctor.mvp.views

import com.ktmmoe.shared.data.vos.ConsultationRequest
import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 29, November, 2020
 **/
interface MainView: BaseView {
    fun bindDoctorInfo(doctor: Doctor)
    fun hideNewConsultationRequest()
    fun hideOldConsultationRequest()
    fun showConsultationRequests(consultationRequests: List<ConsultationRequest>)
    fun showConsultationRecord(data: List<Consultation>)
    fun showEmptyScheduleRecord()
    fun hideEmptyScheduleRecord()
    fun showSelectTimeDialog()
    fun navigateToPatientInfo(consultationRequest: ConsultationRequest)
    fun showPrescriptionDialog(consultationRecord: Consultation, prescriptions: List<PrescriptMedicine>)
    fun showMedicalHistoryDialog(consultationRecord: Consultation)
    fun navigateToConsultationScreen(consultationRecord: Consultation)
    fun navigateToProfileScreen(doctor: Doctor)
    fun navigateToMedicalRecordScreen(consultationRecord: Consultation, remark: String)
}