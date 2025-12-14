package com.habiba.studysmart.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.habiba.studysmart.data.dataSource.local.database.IDatabaseServices
import com.habiba.studysmart.data.utils.remoteMappers.mapFirebaseError
import com.habiba.studysmart.data.dataSource.local.sharedPreference.IAppPreference
import com.habiba.studysmart.data.dataSource.remote.firebase.IFirebaseAuthentication
import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val appPreference: IAppPreference,
    private val firebaseAuthentication: IFirebaseAuthentication,
    private val firebaseAuth: FirebaseAuth,
    private val databaseServices: IDatabaseServices
) : IAuthenticationRepository {

    override fun checkUserExistence(): Boolean {
        return !appPreference.getUserId().isNullOrEmpty()
    }

    override fun getUserId(): String? {
        return appPreference.getUserId()
    }

    override suspend fun signup(
        username: String,
        email: String,
        password: String
    ): Result<FirebaseUser?> {
        return firebaseAuthentication.createUser(username, email, password)
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<FirebaseUser?> {
        return firebaseAuthentication.getUser(email, password)
    }

    override fun saveUserIdToPreference(uid: String) {
        appPreference.putUserId(uid)
    }

    override fun logout() {
        appPreference.clearUserSession()
    }

    override suspend fun isUserInDB(userId: String): Boolean {
        return databaseServices.isUserExists(userId)
    }

}
