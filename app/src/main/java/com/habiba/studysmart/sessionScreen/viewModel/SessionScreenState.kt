package com.habiba.studysmart.sessionScreen.viewModel


import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.model.SubjectIdNameDomainModel
import com.habiba.studysmart.sessionScreen.utils.SessionPhase

data class SessionScreenState(
    val phase: SessionPhase = SessionPhase.IDLE,

    val elapsedSeconds: Long = 0,

    val subjects: List<SubjectIdNameDomainModel> = emptyList(),
    val selectedSubject: SubjectIdNameDomainModel? = null,

    val historySessions: List<SessionDomainModel> = emptyList(),

    val isLoading: Boolean = false,
    val error: String? = null
)
