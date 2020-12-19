package com.ktmmoe.shared.network

import com.ktmmoe.shared.network.requests.FCMRequest
import com.ktmmoe.shared.utils.fcmBaseUrl
import com.ktmmoe.shared.utils.fcmServerKey
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by ktmmoe on 11, December, 2020
 **/

interface FCMApi {
    @Headers("Authorization: key=$fcmServerKey","Content-Type: application/json")
    @POST("fcm/send")
    fun sendNotification(@Body body: FCMRequest): Observable<Map<String, Any>>
}