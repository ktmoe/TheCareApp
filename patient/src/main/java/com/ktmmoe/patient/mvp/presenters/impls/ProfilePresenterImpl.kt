package com.ktmmoe.patient.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.ProfilePresenter
import com.ktmmoe.patient.mvp.views.ProfileView
import com.ktmmoe.shared.data.models.AuthenticationModel
import com.ktmmoe.shared.data.models.PatientModel
import com.ktmmoe.shared.data.models.impls.AuthenticationModelImpl
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 13, December, 2020
 **/
class ProfilePresenterImpl: ProfilePresenter, AbstractBasePresenter<ProfileView>() {
    private var mPatient: Patient? = null
    private val mPatientModel: PatientModel = PatientModelImpl
    private val mAuthModel: AuthenticationModel = AuthenticationModelImpl

    override fun onTapEditProfile() {
        mPatient?.let {
            mView.navigateToEditProfileScreen(it)
        }
    }

    override fun onTapLogout() {
        mAuthModel.logout { mView.navigateToLoginScreen() }
    }

    override fun onTapChangePassword() {
        mView.showSnackBar("Change Password.")
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        mPatient = mPatientModel.getPersistedPatient().first()
        mPatient?.let {
            mPatientModel.observePatientById(it.id, { p ->
                mPatient = p
                mView.showPatientInfo(p)
                if (p.dob.isNullOrEmpty()) mView.showNoPatientInfoDialog()
            }, {
                mView.showSnackBar(it)
            })
        }
    }
}