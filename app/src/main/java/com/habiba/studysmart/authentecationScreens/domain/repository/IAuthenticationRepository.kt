package com.habiba.studysmart.authentecationScreens.domain.repository

interface IAuthenticationRepository {
    fun checkUserExistence(): Boolean
    fun getUserId(): String?
    suspend fun signup(username:String,email:String,password:String): String?
    suspend fun login(email: String,password: String):String?
    fun saveUserIdToPreference()
}