package com.habiba.studysmart.data.remote.dataSource

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FirebaseAuthentication @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): IFirebaseAuthentication {

    override suspend fun createUser(
        userName: String,
        email: String,
        password: String
    ): Result<FirebaseUser?>{
        return try {
            val result = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()

            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun getUser(
        email: String,
        password: String):Result<FirebaseUser?> {
        return try{
            val result = firebaseAuth
                .signInWithEmailAndPassword(email,password)
                .await()
            Result.success(result.user)
        }catch (e: Exception){
            Result.failure(e)
        }

    }

    override suspend fun deleteUser(): Result<Unit> {
        return try {
            firebaseAuth.currentUser?.delete()?.await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}