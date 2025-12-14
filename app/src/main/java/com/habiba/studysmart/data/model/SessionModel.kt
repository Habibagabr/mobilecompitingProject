package com.habiba.studysmart.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "study_session_table",
    foreignKeys = [
        ForeignKey(
            entity= SubjectModel::class,
            parentColumns = ["subjectId"],
            childColumns = ["sessionSubjectId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("sessionSubjectId")]
)
data class SessionModel(
    @PrimaryKey(autoGenerate = true)
    val sessionId: Int=0,
    val sessionSubjectId: Int,
    val relatedToSubject: String? = null,
    val date: String?=null,
    val duration: Long?=null,
)
