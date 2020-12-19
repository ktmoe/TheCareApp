package com.ktmmoe.patient.mvp.presenters

import android.graphics.Bitmap
import com.ktmmoe.patient.mvp.views.EditProfileView
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 13, December, 2020
 **/
interface EditProfilePresenter: BasePresenter<EditProfileView> {
    fun onTapProfilePhoto()
    fun onPhotoTaken(bitmap: Bitmap)
    fun onTapDOB()
    fun onTapSave(name: String, phone: String, bloodType: String, height: String, allergicPrescriptions: String, address: String)
}