package com.habiba.studysmart.authentecationScreens.signup.viewModel

import com.habiba.studysmart.common.strings.EMPTY_STRING

data class SignupScreenState(
    val userName: String = "",
    val userEmail: String = "",
    val userPassword: String = "",
    val confirmPassword: String = "",
    val userNameError: String = "",
    val emailError: String = "",
    val passwordError: String = "",
    val confirmPasswordError: String = "",
    val generalError: String = "",
    val isUserNameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
    val isConfirmPasswordValid: Boolean = true,
    val isGeneralError: Boolean = false,
    val success: Boolean = false,

    val uid: String = ""
)
