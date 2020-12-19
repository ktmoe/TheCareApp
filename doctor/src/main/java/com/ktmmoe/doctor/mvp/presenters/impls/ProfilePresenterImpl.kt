package com.ktmmoe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.doctor.mvp.presenters.ProfilePresenter
import com.ktmmoe.doctor.mvp.views.ProfileView
import com.ktmmoe.shared.data.models.AuthenticationModel
import com.ktmmoe.shared.data.models.DoctorModel
import com.ktmmoe.shared.data.models.impls.AuthenticationModelImpl
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 02, December, 2020
 **/
class ProfilePresenterImpl: ProfilePresenter, AbstractBasePresenter<ProfileView>() {
    private var mDoctor: Doctor = Doctor()
    private val mDoctorModel : DoctorModel = DoctorModelImpl
    private val mAuthModel: AuthenticationModel = AuthenticationModelImpl

    override fun onTapEdit() {
        mView.navigateToEditProfile()
    }

    override fun onTapForgetPassword() {
        mView.showSnackBar("")
    }

    override fun onTapChangePassword() {
        mView.showSnackBar("")
    }

    override fun onTapLogout() {
        mAuthModel.logout {
            mView.navigateToLoginScreen()
        }
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mDoctor = mView.getPassedDoctor()
        mDoctorModel.observeDoctorById(mDoctor.id, {doctor ->
            mDoctor = doctor
            mView.showDoctorProfile(doctor)
        }, {failure->mView.showSnackBar(failure)})
    }
}