package com.habiba.studysmart.subjectScreen.ui.viewModel

import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.model.SubjectDetailsDomainModel
import com.habiba.studysmart.domain.model.TaskDomainModel

data class SubjectScreenState (
    val screenDetails : SubjectDetailsDomainModel ? = null,
    val upComingTasks : List<TaskDomainModel> ? = null ,
    val completedTasks : List<TaskDomainModel> ? =null,
    val studiedSeconds: Long = 0L,
    val recentlyStudiedSession:List<SessionDomainModel> ? = null ,
    val screenLoaded :Boolean = false,
)