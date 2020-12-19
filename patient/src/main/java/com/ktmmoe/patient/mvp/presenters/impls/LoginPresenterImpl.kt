package com.ktmmoe.patient.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.LoginPresenter
import com.ktmmoe.patient.mvp.views.LoginView
import com.ktmmoe.shared.data.models.AuthenticationModel
import com.ktmmoe.shared.data.models.PatientModel
import com.ktmmoe.shared.data.models.impls.AuthenticationModelImpl
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter

/**
 * Created by ktmmoe on 08, December, 2020
 **/
class LoginPresenterImpl: AbstractBasePresenter<LoginView>(), LoginPresenter {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mPatientModel : PatientModel = PatientModelImpl

    override fun onTapLogin(mail: String, password: String) {
        if (validateLoginInput(mail, password)) {
            mAuthenticationModel.login(mail, password,
                {
                    getPatientByIdAndSaveToDatabase()
                }, {
                    mView.showSnackBar(it)
                })
        } else mView.showSnackBar("Please Fill in all fields.")
    }

    override fun onTapRegister() {
        mView.navigateToRegister()
    }

    override fun onTapForgetPassword() {
        mView.showSnackBar("")
    }

    override fun onTapFacebookSignIn() {
        mView.showSnackBar("")
    }

    private fun getPatientByIdAndSaveToDatabase() {
        mAuthenticationModel.getFirebaseUser {
            it?.let {
                mPatientModel.getPatientByIdAndSaveToDatabase(it.uid, onSuccess = {
                    mView.navigateToMain()
                }, onFailure = { error ->
                    mView.showSnackBar(error)
                })
            }
        }
    }

    private fun validateLoginInput(mail: String, password: String) : Boolean =
        (mail.isNotEmpty() || password.isNotEmpty())

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }
}