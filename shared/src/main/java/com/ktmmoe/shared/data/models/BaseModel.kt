package com.ktmmoe.shared.data.models

import android.content.Context
import com.ktmmoe.shared.network.FCMApi
import com.ktmmoe.shared.network.FirebaseApi
import com.ktmmoe.shared.persistance.db.TheCareDB
import com.ktmmoe.shared.utils.fcmBaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ktmmoe on 09, December, 2020
 **/
abstract class BaseModel {
    protected lateinit var mDB: TheCareDB
    abstract var mFirebaseApi: FirebaseApi
    protected var mFCMApi: FCMApi

    init {
        val loggingHeader = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val loggingBody = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val loggingBasic = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingHeader)
            .addInterceptor(loggingBasic)
            .addInterceptor(loggingBody)
            .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(fcmBaseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        mFCMApi = retrofit.create(FCMApi::class.java)
    }

    fun initDatabase(context: Context) {
        mDB = TheCareDB.getDBInstance(context)
    }
}