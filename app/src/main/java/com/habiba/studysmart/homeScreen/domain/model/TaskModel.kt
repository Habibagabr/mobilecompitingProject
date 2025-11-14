package com.habiba.studysmart.homeScreen.domain.model

data class TaskModel(
    val taskTitle:String,
    val description :String ="",
    val taskDue: String,
    val taskPriority:Int,
    val isCompleted:Boolean,
    val relatedToSubject :String ="",
    val taskSubjectId :Int =0,
    val taskId :Int =0
)
