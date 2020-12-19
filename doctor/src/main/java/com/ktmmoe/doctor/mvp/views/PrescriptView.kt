package com.ktmmoe.doctor.mvp.views

import com.ktmmoe.shared.data.vos.Consultation
import com.ktmmoe.shared.data.vos.PrescriptMedicine
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 02, December, 2020
 **/
interface PrescriptView: BaseView {
    fun getPassedExtra(): Consultation
    fun showMedicineList(medicines: List<PrescriptMedicine>)
    fun prescriptAndEndSession(medicines: List<PrescriptMedicine>)
    fun showMedicinePrescriptBox(medicine: PrescriptMedicine, onAddMedicine: (PrescriptMedicine) -> Unit)
}