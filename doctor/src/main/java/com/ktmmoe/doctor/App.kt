package com.ktmmoe.doctor

import android.app.Application
import com.ktmmoe.shared.data.models.impls.DoctorModelImpl

/**
 * Created by ktmmoe on 09, December, 2020
 **/
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DoctorModelImpl.initDatabase(applicationContext)
    }
}