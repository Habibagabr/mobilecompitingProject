package com.habiba.studysmart.authentecationScreens.domain.usecases.utils

sealed class LoginResult {
    data class EmailError(val message: String) : LoginResult()
    data class PasswordError(val message: String) : LoginResult()
    data class GeneralError(val message: String) : LoginResult()
    data class Success(val uid: String) : LoginResult()
}
