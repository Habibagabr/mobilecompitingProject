package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase

import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.authentecationScreens.domain.usecases.utils.SignupResult
import javax.inject.Inject


class SignupUseCase @Inject constructor(
    private val authenticationRepo: IAuthenticationRepository
) : ISignupUseCase {

    override suspend fun invoke(
        userName: String,
        email: String,
        password: String
    ): SignupResult {

        val result = authenticationRepo.signup(userName, email, password)

        return result.fold(
            onSuccess = { user ->
                SignupResult.Success(user?.uid ?: "")
            },
            onFailure = { error ->
                val msg = error.message ?: "Unknown error"
                when {
                    msg.contains("email", ignoreCase = true) ->
                        SignupResult.EmailError(msg)

                    msg.contains("password", ignoreCase = true) ->
                        SignupResult.PasswordError(msg)

                    else ->
                        SignupResult.GeneralError(msg)
                }
            }
        )
    }
}
