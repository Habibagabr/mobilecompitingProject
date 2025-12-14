package com.habiba.studysmart.data.dataSource.remote.firebase

import com.google.firebase.auth.FirebaseUser

interface IFirebaseAuthentication {
    suspend fun createUser(userName:String,email:String,password:String):Result<FirebaseUser?>
    suspend fun getUser(email:String,password:String):Result<FirebaseUser?>
    suspend fun deleteUser(): Result<Unit>
}