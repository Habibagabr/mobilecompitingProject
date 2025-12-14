package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase

import android.util.Log
import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import javax.inject.Inject


class PutUserInSharedPreferences @Inject constructor(
    private val authenticationRepo: IAuthenticationRepository
) : IPutUserInSharedPreferences {

    override suspend fun invoke(uid: String) {
        authenticationRepo.saveUserIdToPreference(uid)
        Log.d("saved to preference ", "$uid")
    }
}
