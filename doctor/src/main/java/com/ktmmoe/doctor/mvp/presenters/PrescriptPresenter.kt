package com.ktmmoe.doctor.mvp.presenters

import com.ktmmoe.doctor.mvp.views.PrescriptView
import com.ktmmoe.doctor.viewholders.PrescriptMedicineViewHolderDelegate
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 02, December, 2020
 **/
interface PrescriptPresenter: BasePresenter<PrescriptView>, PrescriptMedicineViewHolderDelegate {
    fun onTapPrescriptAndEndSession ()
    fun onTapSearchMedicine(input: String)
}