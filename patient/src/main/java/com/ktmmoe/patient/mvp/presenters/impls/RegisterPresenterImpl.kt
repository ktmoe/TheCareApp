package com.ktmmoe.patient.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.patient.mvp.presenters.RegisterPresenter
import com.ktmmoe.patient.mvp.views.RegisterView
import com.ktmmoe.shared.data.models.AuthenticationModel
import com.ktmmoe.shared.data.models.PatientModel
import com.ktmmoe.shared.data.models.impls.AuthenticationModelImpl
import com.ktmmoe.shared.data.models.impls.PatientModelImpl
import com.ktmmoe.shared.data.vos.Patient
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter
import com.ktmmoe.shared.utils.firebaseToken

/**
 * Created by ktmmoe on 09, December, 2020
 **/
class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mPatientModel : PatientModel = PatientModelImpl

    override fun onTapRegister(mail: String, password: String, userName: String) {
        if (mail.isNotEmpty() && password.isNotEmpty() && userName.isNotEmpty()) {
            mAuthenticationModel.register(mail, password, userName,{
                mAuthenticationModel.getFirebaseUser { user->
                    user?.let {
                        var deviceToken = ""
                        firebaseToken {
                            deviceToken = it
                            val id = user.uid
                            val name = user.displayName ?:""
                            val email = user.email?:""
                            val image = user.photoUrl?.path ?:""
                            val patient = Patient(id = id, deviceId = deviceToken, name = name, mail = email, image = image)
                            addPatient(patient)
                        }
                    }
                }
            },
                    {
                        mView.showSnackBar(it)
                    })
        } else mView.showSnackBar("Please fill in all fields.")
    }

    override fun onTapBack() {
        mView.navigateToMain()
    }

    override fun onTapLogin() {
        mView.navigateToMain()
    }

    private fun addPatient(patient: Patient) {
        mPatientModel.addPatient(patient, {
            mPatientModel.getPatientByIdAndSaveToDatabase(patient.id, {
                mView.navigateToMain()
            }, {
                mView.showSnackBar(it)
            })
        },
            {
            mView.showSnackBar(it)
        })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }
}