package com.habiba.studysmart.navigation

import com.habiba.studysmart.domain.model.SubjectDomainModel
import kotlinx.serialization.Serializable

@Serializable
data object SplashScreen

@Serializable
data object OnBoardingScreen

@Serializable
data object LoginScreen

@Serializable
data object SignupScreen

@Serializable
data object Home

@Serializable
data class SubjectScreen(val subjectId:Int)

@Serializable
data class TaskScreen(val subjectId:Int, val subjectName:String)

@Serializable
data class SessionScreen(val userId:String)






