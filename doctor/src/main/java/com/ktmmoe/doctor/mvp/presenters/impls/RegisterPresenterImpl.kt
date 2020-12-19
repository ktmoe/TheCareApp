package com.ktmmoe.doctor.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.ktmmoe.doctor.mvp.presenters.RegisterPresenter
import com.ktmmoe.doctor.mvp.views.RegisterView
import com.ktmmoe.shared.data.models.AuthenticationModel
import com.ktmmoe.shared.data.models.DoctorModel
import com.ktmmoe.shared.data.models.impls.AuthenticationModelImpl
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl
import com.ktmmoe.shared.data.vos.Doctor
import com.ktmmoe.shared.mvp.presenters.AbstractBasePresenter
import com.ktmmoe.shared.utils.firebaseToken

/**
 * Created by ktmmoe on 09, December, 2020
 **/
class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {
    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mDoctorModel : DoctorModel = DoctorModelImpl

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
                            val image = user.photoUrl?.path ?:""
                            addDoctor(Doctor(id = id, deviceId = deviceToken, name = name, mail = mail, image = image))
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
        mView.navigateToCompleteInfo()
    }

    override fun onTapLogin() {
        mView.navigateToCompleteInfo()
    }

    private fun addDoctor(doctor: Doctor) {
        mDoctorModel.addDoctor(doctor, {
            mDoctorModel.getDoctorByIdAndSaveToDatabase(doctor.id, {
                mView.navigateToCompleteInfo()
            }, { mView.showSnackBar(it) })
        },
            { mView.showSnackBar(it) })
    }

    override fun onUiReady(context: Context, owner: LifecycleOwner) {
    }
}