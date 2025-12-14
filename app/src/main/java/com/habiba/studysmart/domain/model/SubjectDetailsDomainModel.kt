package com.habiba.studysmart.domain.model

data class SubjectDetailsDomainModel (
    val subject: SubjectDomainModel?=null,
    val tasks: List<TaskDomainModel>?=null,
    val sessions: List<SessionDomainModel>?=null

)