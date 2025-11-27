package com.habiba.studysmart.authentecationScreens.signup.ui.viewModel

import com.habiba.studysmart.common.strings.EMPTY_STRING

data class SignupScreenState (
    val userName:String=EMPTY_STRING,
    val userEmail : String=EMPTY_STRING,
    val userPassword:String=EMPTY_STRING,
    val confirmPassword:String=EMPTY_STRING,
    val userNameError:String=EMPTY_STRING,
    val emailError:String=EMPTY_STRING,
    val passwordError:String=EMPTY_STRING,
    val confirmPasswordError:String=EMPTY_STRING,
    val isUserNameValid: Boolean = true,
    val isEmailValid: Boolean = true ,
    val isPasswordValid: Boolean = true,
    val isConfirmPasswordValid: Boolean = true,
)