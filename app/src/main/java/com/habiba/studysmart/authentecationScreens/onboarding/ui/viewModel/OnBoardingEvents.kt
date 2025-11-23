package com.habiba.studysmart.authentecationScreens.onboarding.ui.viewModel

sealed class OnBoardingEvents(){
    object LoginPressed: OnBoardingEvents()
    object SignUpPressed: OnBoardingEvents()
}