package com.habiba.studysmart.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserModel (
    @PrimaryKey(autoGenerate = false)
    val userId: String,
    val userEmail: String,
    val userPassword: String,
    val userName: String,
    val subjectCount: Int =0,
    val studiedHour: Int =0,
    val studyGoalHour: Int =0
)