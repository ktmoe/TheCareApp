package com.ktmmoe.patient.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.EditProfilePresenter
import com.ktmmoe.patient.mvp.views.EditProfileView
import com.ktmmoe.shared.data.models.PatientModel
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 13, December, 2020
 **/
class EditProfilePresenterImpl: EditProfilePresenter, AbstractBasePresenter<EditProfileView>() {
    private var mBitmap: Bitmap? = null
    private var mPatient: Patient? = null
    private val mPatientModel: PatientModel = PatientModelImpl
    private var mDob: String = ""

    private var apiCalled: Boolean = false

    override fun onTapProfilePhoto() {
        mView.openGallery()
    }

    override fun onPhotoTaken(bitmap: Bitmap) {
        mBitmap = bitmap
        mView.showSelectedPhoto(bitmap)
    }

    override fun onTapDOB() {
        mView.showDatePickerDialog { d, m, y->
            mDob = "$d-$m-$y"
            mView.showPickedDOB(d, m, y)
        }
    }

    override fun onTapSave(
        name: String,
        phone: String,
        bloodType: String,
        height: String,
        allergicPrescriptions: String,
        address: String
    ) {

        val patient = mPatient?.copy(name = name, phone = phone,  bloodType = bloodType, height = height, allergicPrescriptions = allergicPrescriptions, address = address) ?: Patient()
        patient.dob= if (mDob.isNotEmpty()) mDob else patient.dob

        if (name.isNotEmpty() && phone.isNotEmpty() && patient.dob.isNotEmpty() && bloodType.isNotEmpty() && height.isNotEmpty() && allergicPrescriptions.isNotEmpty() && address.isNotEmpty()){
            mBitmap?.let {
                mPatientModel.uploadImage(it, {image->
                    addPatient(patient.copy(image = image))
                }, {f-> mView.showSnackBar(f)})
            } ?: addPatient(patient)
        } else mView.showSnackBar("Make sure to fill in all info.")
    }

    private fun addPatient(patient: Patient) {
        mPatient?.let {
            apiCalled = true
            mPatientModel.addPatient(patient,{
                mView.navigateBackToProfileScreen()
            }, {mView.showSnackBar(it)})
        }
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mPatient = mPatientModel.getPersistedPatient().first()
        mPatient?.let {
            mPatientModel.observePatientById(it.id, { p ->
                mPatient = p
                if (!apiCalled) mView.bindOldProfileInfo(p)
            }, { f -> mView.showSnackBar(f) })
        }
    }
}