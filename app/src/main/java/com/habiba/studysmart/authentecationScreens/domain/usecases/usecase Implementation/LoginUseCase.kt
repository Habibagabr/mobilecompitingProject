package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase

import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.authentecationScreens.domain.usecases.utils.LoginResult
import com.habiba.studysmart.domain.repository.IHomeRepository
import javax.inject.Inject


class LoginUseCase @Inject constructor(
    private val authenticationRepo: IAuthenticationRepository
) : ILoginUseCase {

    override suspend fun invoke(
        email: String,
        password: String
    ): LoginResult {

        val result = authenticationRepo.login(email, password)

        return result.fold(
            onSuccess = { user ->
                // Success → return UID
                LoginResult.Success(user?.uid ?: "")
            },
            onFailure = { error ->
                val msg = error.message ?: "Unknown error"

                when {
                    msg.contains("email", ignoreCase = true) ->
                        LoginResult.EmailError(msg)

                    msg.contains("password", ignoreCase = true) ->
                        LoginResult.PasswordError(msg)

                    else ->
                        LoginResult.GeneralError(msg)
                }
            }
        )
    }
}
