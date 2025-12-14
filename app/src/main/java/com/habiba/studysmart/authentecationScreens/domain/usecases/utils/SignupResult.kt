package com.habiba.studysmart.authentecationScreens.domain.usecases.utils

sealed class SignupResult {
    data class EmailError(val message: String) : SignupResult()
    data class PasswordError(val message: String) : SignupResult()
    data class GeneralError(val message: String) : SignupResult()

    data class Success(val uid: String) : SignupResult()
}
