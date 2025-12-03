package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase

import com.habiba.studysmart.authentecationScreens.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.authentecationScreens.domain.usecases.utils.SignupResult
import javax.inject.Inject


class SignupUseCase@Inject constructor(
    private val authenticationRepo : IAuthenticationRepository
): ISignupUseCase {
    override suspend fun invoke(userName:String,email: String, password: String): SignupResult {
        val result =  authenticationRepo.signup(userName,email,password)

        return if(result==null) SignupResult.Success

        else {
            return if ((result).lowercase().contains("email"))
                SignupResult.EmailError(result)
            else if((result).lowercase().contains("password"))
                SignupResult.PasswordError(result)
            else
                SignupResult.GeneralError(result)
        }
    }
}