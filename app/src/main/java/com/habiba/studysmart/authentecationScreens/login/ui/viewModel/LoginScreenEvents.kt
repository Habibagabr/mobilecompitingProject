package com.habiba.studysmart.authentecationScreens.login.ui.viewModel

sealed class LoginScreenEvents {
    class EmailChanged(val email:String):LoginScreenEvents()
    class PasswordChanged(val password :String):LoginScreenEvents()
    class LoginPressed():LoginScreenEvents()
}