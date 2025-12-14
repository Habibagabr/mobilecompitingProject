package com.habiba.studysmart.domain.model

import com.habiba.studysmart.homeScreen.util.SubjectsColors

data class SubjectDomainModel(
    val id: Int? = null,
    val userOwnerId: String,
    val name: String,
    val goalHours: Int,
    val actualHours: Int,
    val colorHex: SubjectsColors
)
