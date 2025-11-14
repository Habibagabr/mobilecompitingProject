package com.habiba.studysmart.homeScreen.domain.model

import androidx.compose.ui.graphics.Color

data class SubjectModel (
    val name: String,
    val goalHours: Float,
    val subjectId: Int? = null,
    val subjectColor: List<Color>?=null
)

