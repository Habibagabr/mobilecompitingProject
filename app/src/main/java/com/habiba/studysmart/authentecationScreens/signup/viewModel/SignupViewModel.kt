package com.habiba.studysmart.authentecationScreens.signup.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.ISignupUseCase
import com.habiba.studysmart.authentecationScreens.domain.usecases.utils.SignupResult
import com.habiba.studysmart.common.strings.EMPTY_STRING
import com.habiba.studysmart.common.strings.INVALID_CONFIRM_PASSWORD_SYNTAX
import com.habiba.studysmart.common.strings.REQUIRED_FIELDS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUser: ISignupUseCase
) : ViewModel() {

    private val _signupState = MutableStateFlow(SignupScreenState())
    val signupState: StateFlow<SignupScreenState> = _signupState


    // ------------------------------------------------------
    // EVENT HANDLER
    // ------------------------------------------------------
    fun onEvent(event: SignupScreenEvents) {
        when (event) {
            is SignupScreenEvents.UserNameChanged -> updateUserName(event.userName)
            is SignupScreenEvents.EmailChanged -> updateEmail(event.email)
            is SignupScreenEvents.PasswordChanged -> updatePassword(event.password)
            is SignupScreenEvents.ConfirmPasswordChanged -> updateConfirmPassword(event.confirmPassword)
            is SignupScreenEvents.SignupPressed -> validateAndSignup()
        }
    }


    // ------------------------------------------------------
    // FIELD UPDATE FUNCTIONS
    // ------------------------------------------------------
    private fun updateUserName(value: String) {
        _signupState.value = _signupState.value.copy(userName = value)
    }

    private fun updateEmail(value: String) {
        _signupState.value = _signupState.value.copy(userEmail = value)
    }

    private fun updatePassword(value: String) {
        _signupState.value = _signupState.value.copy(userPassword = value)
    }

    private fun updateConfirmPassword(value: String) {
        _signupState.value = _signupState.value.copy(confirmPassword = value)
    }

    private fun validateAndSignup() {
        resetErrors()

        val state = _signupState.value

        // 1) Required fields
        if (
            state.userName.isBlank() ||
            state.userEmail.isBlank() ||
            state.userPassword.isBlank() ||
            state.confirmPassword.isBlank()
        ) {
            _signupState.value = _signupState.value.copy(
                generalError = REQUIRED_FIELDS,
                isGeneralError = true
            )
            return
        }

        // 2) Confirm password match (only local validation)
        val confirmError = validateConfirmPassword(
            password = state.userPassword,
            confirmPassword = state.confirmPassword
        )

        if (confirmError.isNotEmpty()) {
            _signupState.value = _signupState.value.copy(
                confirmPasswordError = confirmError,
                isConfirmPasswordValid = false
            )
            return
        }

        // 3) All validations passed → Call Firebase through use case
        viewModelScope.launch {
            performSignup()
        }
    }


    // ------------------------------------------------------
    // CALL USE CASE (FIREBASE HANDLING)
    // ------------------------------------------------------
    private suspend fun performSignup() {
        val state = _signupState.value

        val result = signupUser(
            userName = state.userName,
            email = state.userEmail,
            password = state.userPassword
        )

        when (result) {

            is SignupResult.EmailError -> _signupState.value = _signupState.value.copy(
                emailError = result.message,
                isEmailValid = false
            )

            is SignupResult.PasswordError -> _signupState.value = _signupState.value.copy(
                passwordError = result.message,
                isPasswordValid = false
            )

            is SignupResult.GeneralError -> _signupState.value = _signupState.value.copy(
                generalError = result.message,
                isGeneralError = true
            )

            SignupResult.Success -> _signupState.value = _signupState.value.copy(
                userNameError = "",
                emailError = "",
                passwordError = "",
                confirmPasswordError = "",
                isUserNameValid = true,
                isEmailValid = true,
                isPasswordValid = true,
                isConfirmPasswordValid = true
            )
        }
    }


    // ------------------------------------------------------
    // HELPERS
    // ------------------------------------------------------
    private fun validateConfirmPassword(password: String, confirmPassword: String): String {
        return if (password != confirmPassword) INVALID_CONFIRM_PASSWORD_SYNTAX else EMPTY_STRING
    }

    private fun resetErrors() {
        _signupState.value = _signupState.value.copy(
            generalError = "",
            emailError = "",
            passwordError = "",
            confirmPasswordError = "",
            userNameError = "",
            isGeneralError = false,
            isEmailValid = true,
            isPasswordValid = true,
            isConfirmPasswordValid = true,
            isUserNameValid = true
        )
    }
}
