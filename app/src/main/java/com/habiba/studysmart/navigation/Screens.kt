package com.habiba.studysmart.navigation

import kotlinx.serialization.Serializable

@Serializable
enum class AuthenticationScreens{
    Login,
    SignUp
}

@Serializable
data object SplashScreen

@Serializable
data object OnBoardingScreen

@Serializable
data object LoginScreen

@Serializable
data object SignupScreen






