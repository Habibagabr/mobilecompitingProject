package com.habiba.studysmart.splashScreen.viewModel

import androidx.lifecycle.ViewModel
import com.habiba.studysmart.domain.usecases.usecase.ICheckUserExistenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel@Inject constructor(
    private val checkUserExistenceUseCase: ICheckUserExistenceUseCase
): ViewModel() {
    private val _state : MutableStateFlow<SplashScreenState> = MutableStateFlow(SplashScreenState())
    val state : StateFlow<SplashScreenState> = _state




    fun onEvent(event : SplashScreenEvents){
        when(event){
            is SplashScreenEvents.Loading -> loading()
        }

    }

    private fun loading() {
        val isUserExist = checkUserExistenceUseCase()
        if(isUserExist){
            // we have here to navigate to home screen with the user id found
            _state.value = _state.value.copy(
                loading = false,
                userExistence = true
            )
        }
        else {
            // we have here to navigate to login screen
            _state.value = _state.value.copy(
                loading = false ,
                userExistence = false
            )

        }

    }


}