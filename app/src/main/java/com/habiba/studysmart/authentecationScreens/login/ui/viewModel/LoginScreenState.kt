package com.habiba.studysmart.authentecationScreens.login.ui.viewModel

data class LoginScreenState (
    val userEmail : String="",
    val userPassword:String="",
    val emailError:String="",
    val passwordError:String="",
    val isEmailValid: Boolean = true ,
    val isPasswordValid: Boolean = true
)
