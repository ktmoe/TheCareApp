package com.ktmmoe.shared.network.auth

import com.google.firebase.auth.FirebaseUser

/**
 * Created by ktmmoe on 08, December, 2020
 **/
interface AuthManager {
    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun register(email: String, password: String, userName: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun getFirebaseUser(): FirebaseUser?
    fun logout(onSuccess: () -> Unit)
}