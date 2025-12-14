package com.habiba.studysmart.authentecationScreens.signup.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.ICreateNewUser
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.IPutUserInSharedPreferences
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.ISignupUseCase
import com.habiba.studysmart.authentecationScreens.domain.usecases.utils.SignupResult
import com.habiba.studysmart.common.strings.INVALID_CONFIRM_PASSWORD_SYNTAX
import com.habiba.studysmart.common.strings.REQUIRED_FIELDS
import com.habiba.studysmart.data.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUser: ISignupUseCase,
    private val putUserInSharedPreferences: IPutUserInSharedPreferences,
    private val createNewUserInDB: ICreateNewUser
) : ViewModel() {

    private val _signupState = MutableStateFlow(SignupScreenState())
    val signupState: StateFlow<SignupScreenState> = _signupState

    fun onEvent(event: SignupScreenEvents) {
        when (event) {
            is SignupScreenEvents.UserNameChanged -> updateUserName(event.userName)
            is SignupScreenEvents.EmailChanged -> updateEmail(event.email)
            is SignupScreenEvents.PasswordChanged -> updatePassword(event.password)
            is SignupScreenEvents.ConfirmPasswordChanged -> updateConfirmPassword(event.confirmPassword)
            SignupScreenEvents.SignupPressed -> validateAndSignup()
            is SignupScreenEvents.SuccessSignUp -> successfulSignup(event.uid)
        }
    }

    private fun successfulSignup(uid: String) {
        viewModelScope.launch {

            putUserInSharedPreferences(uid)

            createNewUserInDB(
                UserModel(
                    userEmail = signupState.value.userEmail,
                    userId = uid,
                    userPassword = signupState.value.userPassword,
                    userName = signupState.value.userName,
                    subjectCount = 0,
                    studiedHour = 0,
                    studyGoalHour = 0

                )
            )
        }
    }

    private fun validateAndSignup() {
        resetErrors()

        val s = signupState.value

        if (s.userName.isBlank() ||
            s.userEmail.isBlank() ||
            s.userPassword.isBlank() ||
            s.confirmPassword.isBlank()
        ) {
            _signupState.value = s.copy(
                generalError = REQUIRED_FIELDS,
                isGeneralError = true
            )
            return
        }

        if (s.userPassword != s.confirmPassword) {
            _signupState.value = s.copy(
                confirmPasswordError = INVALID_CONFIRM_PASSWORD_SYNTAX,
                isConfirmPasswordValid = false
            )
            return
        }

        viewModelScope.launch { performSignup() }
    }

    private suspend fun performSignup() {
        val s = signupState.value

        val result = signupUser(
            userName = s.userName,
            email = s.userEmail,
            password = s.userPassword
        )

        when (result) {

            is SignupResult.EmailError ->
                _signupState.value = s.copy(emailError = result.message, isEmailValid = false)

            is SignupResult.PasswordError ->
                _signupState.value = s.copy(passwordError = result.message, isPasswordValid = false)

            is SignupResult.GeneralError ->
                _signupState.value = s.copy(generalError = result.message, isGeneralError = true)

            is SignupResult.Success -> {
                _signupState.value = _signupState.value.copy(
                    success = true,
                    uid = result.uid
                )
            }
        }
    }

    private fun updateUserName(value: String) {
        _signupState.value = signupState.value.copy(userName = value)
    }

    private fun updateEmail(value: String) {
        _signupState.value = signupState.value.copy(userEmail = value)
    }

    private fun updatePassword(value: String) {
        _signupState.value = signupState.value.copy(userPassword = value)
    }

    private fun updateConfirmPassword(value: String) {
        _signupState.value = signupState.value.copy(confirmPassword = value)
    }

    private fun resetErrors() {
        _signupState.value = signupState.value.copy(
            generalError = "",
            emailError = "",
            passwordError = "",
            confirmPasswordError = "",
            isGeneralError = false,
            isEmailValid = true,
            isPasswordValid = true,
            isConfirmPasswordValid = true
        )
    }
}
