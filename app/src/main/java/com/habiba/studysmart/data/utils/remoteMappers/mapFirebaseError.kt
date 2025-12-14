package com.habiba.studysmart.data.utils.remoteMappers

import com.google.firebase.auth.*

fun mapFirebaseError(e: Exception): String {

    if (e is FirebaseAuthException) {

        return when (e.errorCode) {

            "ERROR_INVALID_EMAIL" ->
                "The email address is not valid."

            "ERROR_INVALID_CREDENTIAL" ->
                "Email or password is incorrect."

            "ERROR_USER_NOT_FOUND" ->
                "No user found for that email."

            "ERROR_WRONG_PASSWORD" ->
                "Wrong password provided for this user."

            "ERROR_EMAIL_ALREADY_IN_USE" ->
                "This email is already registered."

            "ERROR_WEAK_PASSWORD" ->
                "Password is too weak. Choose at least 6+ characters."

            "ERROR_TOO_MANY_REQUESTS" ->
                "Too many attempts. Please try again later."

            else -> e.message ?: "Authentication error occurred."
        }
    }

    return e.message ?: "Unknown authentication error"
}
