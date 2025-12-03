package com.habiba.studysmart.authentecationScreens.domain.usecases.utils

sealed class SignupResult() {
    object Success : SignupResult()

    data class EmailError(val message: String) : SignupResult()

    data class PasswordError(val message: String) : SignupResult()

    data class GeneralError(val message: String) : SignupResult()

}