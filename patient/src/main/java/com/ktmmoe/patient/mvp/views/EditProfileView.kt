package com.ktmmoe.patient.mvp.views

import android.graphics.Bitmap
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 13, December, 2020
 **/
interface EditProfileView: BaseView {
    fun bindOldProfileInfo(patient: Patient)
    fun showSelectedPhoto(bitmap: Bitmap)
    fun navigateBackToProfileScreen()
    fun openGallery()
    fun showDatePickerDialog(onPicked: (Int, Int, Int) -> Unit)
    fun showPickedDOB(day: Int, month: Int, year: Int)
}