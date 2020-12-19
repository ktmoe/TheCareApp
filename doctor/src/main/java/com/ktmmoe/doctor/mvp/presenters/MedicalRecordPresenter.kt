package com.ktmmoe.doctor.mvp.presenters

import com.ktmmoe.doctor.mvp.views.MedicalRecordView
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 03, December, 2020
 **/
interface MedicalRecordPresenter : BasePresenter<MedicalRecordView> {
    fun onTapSave(remark: String)
}