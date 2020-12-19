package com.ktmmoe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.doctor.mvp.presenters.MedicalRecordPresenter
import com.ktmmoe.doctor.mvp.views.MedicalRecordView
import com.ktmmoe.shared.data.models.DoctorModel
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 03, December, 2020
 **/
class MedicalRecordPresenterImpl: MedicalRecordPresenter, AbstractBasePresenter<MedicalRecordView>() {
    private val mDoctorModel : DoctorModel = DoctorModelImpl

    override fun onTapSave(remark: String) {
        mDoctorModel.addConsultationRemark(mView.getPassedConsultationRecord().id, remark, {
            mView.showSnackBar("Remark Added.")
        }, {
            mView.showSnackBar(it)
        })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mView.showConsultationRecord(mView.getPassedConsultationRecord())
    }
}