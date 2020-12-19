package com.ktmmoe.shared.data.models

import com.google.firebase.auth.FirebaseUser
import com.ktmmoe.shared.network.auth.AuthManager

/**
 * Created by ktmmoe on 08, December, 2020
 **/
interface AuthenticationModel {
    var mAuthManager: AuthManager

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun register(
            email: String,
            password: String,
            userName: String,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    )

    fun getFirebaseUser(onSuccess: (FirebaseUser?) -> Unit)

    fun logout(onSuccess: () -> Unit)
}