package com.ktmmoe.shared.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging
import com.ktmmoe.shared.R
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by ktmmoe on 29, November, 2020
 **/
fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun String.debugLog(data: String) {
    Log.d(this, data)
}

fun ImageView.load(url: String) {
    Glide.with(this.context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_profile).error(R.drawable.ic_profile)
        )
        .load(url)
        .into(this)
}

fun CircleImageView.load(url: String) {
    Glide.with(this.context)
            .setDefaultRequestOptions(
                    RequestOptions().placeholder(R.drawable.ic_profile).error(R.drawable.ic_profile)
            )
            .load(url)
            .into(this)
}

fun ImageView.load(bitmap: Bitmap) {
    Glide.with(this.context)
            .load(bitmap)
            .into(this)
}

fun firebaseToken(onSuccess:(String)->Unit){
    FirebaseMessaging.getInstance().token
        .addOnSuccessListener { onSuccess(it) }
}