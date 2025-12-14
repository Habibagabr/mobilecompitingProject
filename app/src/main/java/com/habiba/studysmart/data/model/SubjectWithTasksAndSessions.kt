package com.habiba.studysmart.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class SubjectWithTasksAndSessions (
    @Embedded
    val subject : SubjectModel,

    @Relation(
        parentColumn = "subjectId",
        entityColumn = "taskSubjectId"
    )
    val subjectTasks:List<TaskModel>,

    @Relation(
        parentColumn = "subjectId",
        entityColumn = "sessionSubjectId"
    )
    val subjectSessions: List<SessionModel>
)