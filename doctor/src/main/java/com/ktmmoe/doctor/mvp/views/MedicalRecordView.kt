package com.ktmmoe.doctor.mvp.views

import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 03, December, 2020
 **/
interface MedicalRecordView: BaseView {
    fun getPassedConsultationRecord(): Consultation
    fun getPassedRemark(): String
    fun showConsultationRecord(consultationRecord: Consultation)
}