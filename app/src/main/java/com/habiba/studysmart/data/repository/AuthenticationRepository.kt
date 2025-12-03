package com.habiba.studysmart.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.habiba.studysmart.data.utils.mapFirebaseError
import com.habiba.studysmart.data.local.dataSource.IAppPreference
import com.habiba.studysmart.data.remote.dataSource.IFirebaseAuthentication
import com.habiba.studysmart.authentecationScreens.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class AuthenticationRepository@Inject constructor(
    private val appPreference : IAppPreference,
    private val firebaseAuthentication : IFirebaseAuthentication,
    private val firebaseAuthObj: FirebaseAuth
): IAuthenticationRepository {

    override fun checkUserExistence():Boolean {
        val userId= appPreference.getUserId()
        return !userId.isNullOrEmpty()
    }

    override fun getUserId(): String? {
        return appPreference.getUserId()
    }

    override suspend fun signup(username:String,email: String, password: String): String? {
        val result = firebaseAuthentication.createUser(username,email,password)
       return result.fold(
            onSuccess = { user ->
                // Return null → means NO ERROR (success)
                null
            },
            onFailure= { error -> mapFirebaseError(error as Exception ) }
        )
    }

    override suspend fun login(email: String, password: String):String?{
       val result =  firebaseAuthentication.getUser(email,password)
        return result.fold(
            onSuccess = { user ->
                // Return null → means NO ERROR (success)
                null
            },
            onFailure= { error -> mapFirebaseError(error as Exception ) }
        )


    }

    override fun saveUserIdToPreference() {
        val userId =  firebaseAuthObj.currentUser?.uid
        appPreference.putUserId(userId)
    }


}