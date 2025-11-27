package com.habiba.studysmart.onboarding.ui.viewModel

sealed class OnBoardingEvents(){
    object LoginPressed: OnBoardingEvents()
    object SignUpPressed: OnBoardingEvents()
}