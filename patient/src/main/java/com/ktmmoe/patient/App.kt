package com.ktmmoe.patient

import android.app.Application
import com.ktmmoe.shared.data.models.impls.PatientModelImpl

/**
 * Created by ktmmoe on 09, December, 2020
 **/
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        PatientModelImpl.initDatabase(applicationContext)
    }
}