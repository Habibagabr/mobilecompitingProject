package com.habiba.studysmart.authentecationScreens.signup.ui.viewModel

import androidx.lifecycle.ViewModel
import com.habiba.studysmart.common.strings.EMPTY_STRING
import com.habiba.studysmart.common.strings.GMAIL_REGEX
import com.habiba.studysmart.common.strings.INVALID_CONFIRM_PASSWORD_SYNTAX
import com.habiba.studysmart.common.strings.INVALID_EMAIL_LENGTH
import com.habiba.studysmart.common.strings.INVALID_EMAIL_SYNTAX
import com.habiba.studysmart.common.strings.INVALID_PASSWORD_LENGTH
import com.habiba.studysmart.common.strings.INVALID_USERNAME_SYNTAX
import com.habiba.studysmart.common.strings.MIN_PASS_LENGTH
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignupViewModel(): ViewModel() {
    private val _signupState = MutableStateFlow(SignupScreenState())
    val signupState: StateFlow<SignupScreenState> = _signupState

    fun onEvent(events: SignupScreenEvents){
        when(events){
            is SignupScreenEvents.UserNameChanged -> onUserNameChanged(events.userName)
            is SignupScreenEvents.EmailChanged -> onEmailChanged(events.email)
            is SignupScreenEvents.PasswordChanged -> onPasswordChanged(events.password)
            is SignupScreenEvents.ConfirmPasswordChanged -> onConfirmPasswordChanged(events.confirmPassword)
            is SignupScreenEvents.SignupPressed -> onSignupPressed()
        }
    }


    private fun onUserNameChanged(userName: String) {
        _signupState.value = _signupState.value.copy(
            userName = userName
        )

    }
    private fun onEmailChanged(email: String) {
        _signupState.value = _signupState.value.copy(
            userEmail = email
        )

    }
    private fun onPasswordChanged(password: String) {
        _signupState.value = _signupState.value.copy(
            userPassword = password
        )

    }
    private fun onConfirmPasswordChanged(confirmPassword: String) {
        _signupState.value = _signupState.value.copy(
            confirmPassword = confirmPassword
        )
    }

    private fun onSignupPressed() {
        val userName = _signupState.value.userName
        val email = _signupState.value.userEmail
        val password = _signupState.value.userPassword
        val confirmPassword = _signupState.value.confirmPassword
        val userNameError = validateUserName(userName)
        val emailError = validateEmail(email)
        val passwordError = validatePassword(password)
        val confirmPasswordError = validateConfirmPassword(password,confirmPassword)

        _signupState.value = _signupState.value.copy(
            userNameError = userNameError,
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError,
            isUserNameValid = userNameError.isEmpty(),
            isEmailValid = emailError.isEmpty(),
            isPasswordValid = passwordError.isEmpty(),
            isConfirmPasswordValid = confirmPasswordError.isEmpty()
        )

    }

    private fun validateUserName(userName: String):String {
        // not previously found in data base
        return if(userName.isBlank()) INVALID_USERNAME_SYNTAX else EMPTY_STRING
    }
    private fun validateEmail(email: String):String {
        // then we will check from the data base or fire base
        return if(email.length <= MIN_PASS_LENGTH){
            INVALID_EMAIL_LENGTH
        } else{
            if(!(Regex(GMAIL_REGEX).matches(email))){
                INVALID_EMAIL_SYNTAX
            } else{
                EMPTY_STRING
            }
        }

    }
    private fun validatePassword(password: String):String {
        // then we will check from the data base or fire base
        return if (password.length <=9) INVALID_PASSWORD_LENGTH else EMPTY_STRING

    }
    private fun validateConfirmPassword(password: String, confirmPassword: String):String {
        return if (password != confirmPassword) INVALID_CONFIRM_PASSWORD_SYNTAX else EMPTY_STRING

    }

}