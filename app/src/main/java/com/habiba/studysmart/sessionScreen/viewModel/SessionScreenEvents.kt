package com.habiba.studysmart.sessionScreen.viewModel

import com.habiba.studysmart.domain.model.SubjectIdNameDomainModel


sealed interface SessionScreenEvents {

    object ScreenStarted : SessionScreenEvents

    object StartBtnClicked : SessionScreenEvents
    object StopBtnClicked : SessionScreenEvents
    object ResumeBtnClicked : SessionScreenEvents
    object FinishBtnClicked : SessionScreenEvents
    object CancelBtnClicked : SessionScreenEvents

    data class SubjectSelected(
        val subject: SubjectIdNameDomainModel
    ) : SessionScreenEvents
}
