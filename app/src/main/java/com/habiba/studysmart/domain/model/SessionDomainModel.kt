package com.habiba.studysmart.domain.model

data class SessionDomainModel(
    val sessionId: Int?=null,
    val subjectId: Int,
    val relatedToSubject: String?,
    val date: String?,
    val duration: Long?
)
