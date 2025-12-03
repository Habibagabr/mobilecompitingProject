package com.habiba.studysmart.data.utils

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

fun mapFirebaseError(e: Exception): String {
    return when (e) {

        // -------- Password-related errors --------
        is FirebaseAuthWeakPasswordException ->
            "Password is too weak. Choose at least 6+ characters."

        is FirebaseAuthInvalidCredentialsException ->
            if (e.message?.contains("password") == true)
                "Wrong password. Please try again."
            else
                "Invalid email format."

        // -------- Email-related errors --------
        is FirebaseAuthUserCollisionException ->
            "This email is already registered."

        is FirebaseAuthInvalidUserException ->
            "This email is not registered."

        // -------- Network errors --------
        is FirebaseNetworkException ->
            "Please check your internet connection."

        // -------- Too many attempts --------
        is FirebaseAuthException ->
            when (e.errorCode) {
                "ERROR_TOO_MANY_REQUESTS" ->
                    "Too many attempts. Please try again later."

                "ERROR_OPERATION_NOT_ALLOWED" ->
                    "This operation is not allowed."
                else -> e.message ?: "Authentication error occurred."
            }

        // -------- Default fallback --------
        else -> e.message ?: "Unknown error occurred"
    }
}
