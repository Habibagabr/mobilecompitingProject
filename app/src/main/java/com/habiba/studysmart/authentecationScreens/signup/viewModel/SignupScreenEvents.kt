package com.habiba.studysmart.authentecationScreens.signup.viewModel

sealed class SignupScreenEvents {
    data class UserNameChanged(val userName: String) : SignupScreenEvents()
    data class EmailChanged(val email: String) : SignupScreenEvents()
    data class PasswordChanged(val password: String) : SignupScreenEvents()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignupScreenEvents()
    object SignupPressed : SignupScreenEvents()

    data class SuccessSignUp(val uid: String) : SignupScreenEvents()
}
