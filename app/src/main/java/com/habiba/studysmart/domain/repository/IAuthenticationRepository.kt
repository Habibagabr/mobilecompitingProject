package com.habiba.studysmart.domain.repository

import com.google.firebase.auth.FirebaseUser

interface IAuthenticationRepository {
    fun checkUserExistence(): Boolean
    fun getUserId(): String?
    suspend fun signup(username: String, email: String, password: String): Result<FirebaseUser?>
    suspend fun login(email: String, password: String): Result<FirebaseUser?>
    fun saveUserIdToPreference(uid: String)

    fun logout()
    suspend fun isUserInDB(userId:String):Boolean

}
