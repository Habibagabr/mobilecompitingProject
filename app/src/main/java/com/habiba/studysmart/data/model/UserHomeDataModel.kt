package com.habiba.studysmart.data.model

import androidx.room.Embedded
import androidx.room.Relation


data class UserHomeDataModel (
    @Embedded
    val user: UserModel,

    @Relation(
        parentColumn = "userId",
        entityColumn = "subjectUserOwnerId",
        entity = SubjectModel::class
    )
    val userDetails:List<SubjectWithTasksAndSessions>
)