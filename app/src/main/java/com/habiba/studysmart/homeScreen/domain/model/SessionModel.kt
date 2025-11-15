package com.habiba.studysmart.homeScreen.domain.model

data class SessionModel(
    val relatedToSubject: String,
    val date: String,
    val duration: Long,
    val sessionSubjectId: Int,
    val sessionId: Int
)
