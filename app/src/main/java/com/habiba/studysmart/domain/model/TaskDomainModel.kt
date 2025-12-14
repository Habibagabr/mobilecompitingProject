package com.habiba.studysmart.domain.model

data class TaskDomainModel (
    val taskId: Int?=null,
    val taskToSubject:String,
    val subjectId: Int,
    val title: String,
    val description: String,
    val priority: Int,
    val isCompleted: Boolean,
    val taskDate: String

)