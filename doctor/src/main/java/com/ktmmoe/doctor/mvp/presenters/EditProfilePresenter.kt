package com.ktmmoe.doctor.mvp.presenters

import android.graphics.Bitmap
import com.ktmmoe.doctor.mvp.views.EditProfileView
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.presenters.BasePresenter

/**
 * Created by ktmmoe on 14, December, 2020
 **/
interface EditProfilePresenter: BasePresenter<EditProfileView> {
    fun onTapProfilePhoto()
    fun onPhotoTaken(bitmap: Bitmap)
    fun onTapDOB()
    fun onTapSave(specialty: String, name: String, phone: String, experience: String, gender: String, address: String, degrees: String, profile: String)
}