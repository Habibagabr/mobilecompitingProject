package com.habiba.studysmart.authentecationScreens.domain.usecases.usecase

import com.habiba.studysmart.authentecationScreens.domain.usecases.utils.SignupResult


interface ISignupUseCase {
    suspend operator fun invoke(
        userName: String,
        email: String,
        password: String
    ): SignupResult
}
