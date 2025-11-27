package com.habiba.studysmart.authentecationScreens.login.ui.viewModel

import androidx.lifecycle.ViewModel
import com.habiba.studysmart.common.strings.EMPTY_STRING
import com.habiba.studysmart.common.strings.GMAIL_REGEX
import com.habiba.studysmart.common.strings.INVALID_EMAIL_LENGTH
import com.habiba.studysmart.common.strings.INVALID_EMAIL_SYNTAX
import com.habiba.studysmart.common.strings.INVALID_PASSWORD_LENGTH
import com.habiba.studysmart.common.strings.MIN_PASS_LENGTH
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(): ViewModel() {
    private val _loginState = MutableStateFlow(LoginScreenState())
    val loginState: StateFlow<LoginScreenState> = _loginState

    fun onEvents(event:LoginScreenEvents){
        when(event){
            is LoginScreenEvents.EmailChanged -> onEmailChanged(event.email)
            is LoginScreenEvents.PasswordChanged -> onPasswordChanged(event.password)
            is LoginScreenEvents.LoginPressed -> onLoginPressed()
        }
    }

    private fun onEmailChanged(email: String) {
        _loginState.value = _loginState.value.copy(
            userEmail = email
        )

    }
    private fun onPasswordChanged(password: String) {
        _loginState.value = _loginState.value.copy(
            userPassword = password
        )

    }
    private fun onLoginPressed() {
        val email = _loginState.value.userEmail
        val password = _loginState.value.userPassword
        val emailError = validateEmail(email)
        val passwordError = validatePassword(password)

        _loginState.value = _loginState.value.copy(
            emailError = emailError,
            passwordError = passwordError,
            isEmailValid = emailError.isEmpty(),
            isPasswordValid = passwordError.isEmpty()
        )
    }

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