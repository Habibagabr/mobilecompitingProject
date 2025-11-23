package com.habiba.studysmart.authentecationScreens.onboarding.ui.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnBoardingViewModel(): ViewModel() {
    private val _onBoardingState : MutableStateFlow<OnBoardingState> = MutableStateFlow(OnBoardingState())
    val onBoardingState : StateFlow<OnBoardingState> = _onBoardingState

    fun onEvent(event: OnBoardingEvents){
        when(event){
            OnBoardingEvents.LoginPressed -> loginPressed()
            OnBoardingEvents.SignUpPressed -> signupPressed()
        }
    }

    private fun signupPressed() {
        _onBoardingState.value=_onBoardingState.value.copy(
            loginPressed = false,
            signUpPressed = true
        )

    }

    private fun loginPressed() {
        _onBoardingState.value=_onBoardingState.value.copy(
            loginPressed = true,
            signUpPressed = false
        )

    }
}