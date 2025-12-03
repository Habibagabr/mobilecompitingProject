package com.habiba.studysmart.authentecationScreens.signup.viewModel

sealed class SignupScreenEvents() {
    class UserNameChanged(val userName:String):SignupScreenEvents()
    class EmailChanged(val email:String):SignupScreenEvents()
    class PasswordChanged(val password :String):SignupScreenEvents()
    class ConfirmPasswordChanged(val confirmPassword :String):SignupScreenEvents()
    class SignupPressed():SignupScreenEvents()
}