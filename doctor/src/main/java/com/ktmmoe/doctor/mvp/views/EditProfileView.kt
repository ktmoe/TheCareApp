package com.ktmmoe.doctor.mvp.views

import android.graphics.Bitmap
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.views.BaseView

/**
 * Created by ktmmoe on 14, December, 2020
 **/
interface EditProfileView: BaseView {
    fun showOldDoctorInfo(doctor: Doctor)
    fun showSpecialtySpinner(array: Array<Speciality>)
    fun showSelectedPhoto(bitmap: Bitmap)
    fun navigateBack()
    fun openGallery()
    fun showDatePickerDialog(onPicked: (Int, Int, Int) -> Unit)
    fun showPickedDOB(day: Int, month: Int, year: Int)
}