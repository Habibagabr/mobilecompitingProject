package com.habiba.studysmart.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "subject_table",
    foreignKeys = [
        ForeignKey(
            entity = UserModel::class,
            parentColumns=["userId"],
            childColumns = ["subjectUserOwnerId"],
            onDelete=  ForeignKey.CASCADE
        )
    ],
    indices = [Index("subjectUserOwnerId")]
)
data class SubjectModel (
    @PrimaryKey(autoGenerate = true)
    val subjectId: Int=0,
    val subjectUserOwnerId:String,
    val subjectName: String,
    val goalHours: Int = 0,
    val actualHours: Int = 0,
    val subjectColor: String
)

