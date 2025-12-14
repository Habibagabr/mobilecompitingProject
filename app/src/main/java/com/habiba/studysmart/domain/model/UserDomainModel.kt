package com.habiba.studysmart.domain.model

data class UserDomainModel (
    val userId: String,
    val userEmail:String,
    val userPassword:String,
    val name: String,
    val subjectCount: Int,
    val studiedHours: Int,
    val studyGoalHour: Int
)