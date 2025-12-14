package com.habiba.studysmart.domain.model

data class UserHomeDataDomainModel(
    val user: UserDomainModel?,
    val details: List<SubjectDetailsDomainModel>?
)
