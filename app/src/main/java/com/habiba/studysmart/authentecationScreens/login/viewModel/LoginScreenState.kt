package com.habiba.studysmart.authentecationScreens.login.viewModel

data class LoginScreenState(
    val userEmail: String = "",
    val userPassword: String = "",
    val emailError: String = "",
    val passwordError: String = "",
    val generalError: String = "",
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val isGeneralError: Boolean = false,
    val success: Boolean = false,

    val uid: String = ""
)
