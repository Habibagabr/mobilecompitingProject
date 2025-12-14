package com.habiba.studysmart.authentecationScreens.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.ICreateNewUser
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.IIsUserExistsUseCase
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.ILoginUseCase
import com.habiba.studysmart.authentecationScreens.domain.usecases.usecase.IPutUserInSharedPreferences
import com.habiba.studysmart.authentecationScreens.domain.usecases.utils.LoginResult
import com.habiba.studysmart.common.strings.REQUIRED_FIELDS
import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IGetUserIdUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: ILoginUseCase,
    private val saveUserInSharedPreferences: IPutUserInSharedPreferences,
    private val createNewUserInDB: ICreateNewUser,
    private val isUserExistsUseCase: IIsUserExistsUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginScreenState())
    val loginState: StateFlow<LoginScreenState> = _loginState

    fun onEvents(event: LoginScreenEvents) {
        when (event) {
            is LoginScreenEvents.EmailChanged ->
                _loginState.value = _loginState.value.copy(userEmail = event.email)

            is LoginScreenEvents.PasswordChanged ->
                _loginState.value = _loginState.value.copy(userPassword = event.password)

            is LoginScreenEvents.LoginPressed ->
                onLoginPressed()
        }
    }

    private fun onLoginPressed() {
        resetErrors()

        val state = _loginState.value

        if (state.userEmail.isBlank() || state.userPassword.isBlank()) {
            _loginState.value = state.copy(
                generalError = REQUIRED_FIELDS,
                isGeneralError = true
            )
            return
        }

        viewModelScope.launch {
            when (val result = loginUseCase(state.userEmail, state.userPassword)) {

                is LoginResult.EmailError ->
                    _loginState.value = state.copy(
                        emailError = result.message,
                        isEmailValid = false
                    )

                is LoginResult.PasswordError ->
                    _loginState.value = state.copy(
                        passwordError = result.message,
                        isPasswordValid = false
                    )

                is LoginResult.GeneralError ->
                    _loginState.value = state.copy(
                        generalError = result.message,
                        isGeneralError = true
                    )

                is LoginResult.Success -> {
                    val uid = result.uid

                    // 1️⃣ save uid locally
                    saveUserInSharedPreferences(uid)

                    // 2️⃣ check if user exists in DB
                    val isUserExists = isUserExistsUseCase(uid)

                    // 3️⃣ create user if not exists
                    if (!isUserExists) {
                        createNewUserInDB(
                            UserModel(
                                userId = uid,
                                userEmail = state.userEmail,
                                userPassword = state.userPassword,
                                userName = state.userEmail.substringBefore("@"),
                                subjectCount = 0,
                                studiedHour = 0,
                                studyGoalHour = 0
                            )
                        )
                    }

                    // 4️⃣ notify UI to navigate
                    _loginState.value = state.copy(
                        success = true,
                        uid = uid
                    )
                }
            }
        }
    }

    private fun resetErrors() {
        _loginState.value = _loginState.value.copy(
            generalError = "",
            emailError = "",
            passwordError = "",
            isGeneralError = false,
            isEmailValid = true,
            isPasswordValid = true,
            success = false
        )
    }
}
