package com.ktmmoe.patient.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ktmmoe.patient.R
import com.ktmmoe.patient.activities.MainActivity

/**
 * Created by ktmmoe on 11, December, 2020
 **/

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val body = message.data["body"]
        val title = message.data["title"]

        showNotification(title,body)

    }


    private fun showNotification(title : String?, body : String?){
        val channelId = "The Care App Notification Channel"
        val channelName = "The Care App Notification Channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pIntent = PendingIntent.getActivity(
            this,0,intent,PendingIntent.FLAG_ONE_SHOT
        )

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            setupNotificationChannel(channelId,channelName,notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this,channelId).apply {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(body)
            setContentIntent(pIntent)
        }

        notificationManager.notify(0,notificationBuilder.build())

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setupNotificationChannel(id : String, name : String, notificationManager: NotificationManager){

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id,name,importance)
        notificationManager.createNotificationChannel(channel)

    }


}