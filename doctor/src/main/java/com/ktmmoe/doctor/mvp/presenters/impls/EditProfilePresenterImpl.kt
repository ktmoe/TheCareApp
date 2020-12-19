package com.ktmmoe.doctor.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.doctor.mvp.presenters.EditProfilePresenter
import com.ktmmoe.doctor.mvp.views.EditProfileView
import com.ktmmoe.shared.data.models.DoctorModel
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.data.vos.Speciality
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 14, December, 2020
 **/
class EditProfilePresenterImpl: EditProfilePresenter, AbstractBasePresenter<EditProfileView>() {
    private var mBitmap: Bitmap? = null
    private var mDoctor: Doctor? = null
    private val mDoctorModel: DoctorModel = DoctorModelImpl
    private var mDob: String = ""
    private var mSpecialty: Speciality? = null
    private var mSpecialties: List<Speciality> = listOf()

    private var apiCalled: Boolean = false

    override fun onTapProfilePhoto() {
        mView.openGallery()
    }

    override fun onPhotoTaken(bitmap: Bitmap) {
        mBitmap = bitmap
        mBitmap?.let { mView.showSelectedPhoto(it) }
    }

    override fun onTapDOB() {
        mView.showDatePickerDialog { d, m, y->
            mDob = "$d-$m-$y"
            mView.showPickedDOB(d, m, y)
        }
    }

    override fun onTapSave(specialty: String, name: String, phone: String, experience: String, gender: String, address: String, degrees: String, profile: String) {

        mSpecialty = mSpecialties.last { it.name == specialty }

        val doctor = mDoctor?.copy(name = name, phone = phone, specialtyName= mSpecialty?.name ?:"", specialtyId = mSpecialty?.id?:"", experience = experience, gender = gender, address = address, degrees = degrees, profile = profile) ?: Doctor()
        doctor.dob= if (mDob.isNotEmpty()) mDob else doctor.dob

        if (name.isNotEmpty() && phone.isNotEmpty() && doctor.dob.isNotEmpty() && specialty.isNotEmpty() && experience.isNotEmpty() && address.isNotEmpty() && degrees.isNotEmpty() && profile.isNotEmpty()){
            mBitmap?.let {
                mDoctorModel.uploadImage(it, { image->
                    addDoctor(doctor.copy(image = image))
                }, {f-> mView.showSnackBar(f)})
            } ?: addDoctor(doctor)
        } else mView.showSnackBar("Make sure to fill in all info.")
    }

    private fun addDoctor(doctor: Doctor) {
        mDoctor?.let {
            apiCalled = true
            mDoctorModel.addDoctor(doctor,{
                mView.navigateBack()
            }, {mView.showSnackBar(it)})
        }
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mDoctor = mDoctorModel.getPersistedDoctor().first()
        mDoctor?.let {
            mDoctorModel.observeDoctorById(it.id, { d ->
                mDoctor = d
                if (!apiCalled) mView.showOldDoctorInfo(d)
            }, { f -> mView.showSnackBar(f) })
        }
        getSpecialities()
    }

    private fun getSpecialities() {
        mDoctorModel.getSpecialities({
            mSpecialties = it
            mView.showSpecialtySpinner(it.toTypedArray())
        }, {
            mView.showSnackBar(it)
        })
    }
}

