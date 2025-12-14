package com.habiba.studysmart.authentecationScreens.login.viewModel

sealed class LoginScreenEvents {
    data class EmailChanged(val email: String) : LoginScreenEvents()
    data class PasswordChanged(val password: String) : LoginScreenEvents()
    object LoginPressed : LoginScreenEvents()

}
