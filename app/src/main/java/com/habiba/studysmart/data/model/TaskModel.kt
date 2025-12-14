package com.habiba.studysmart.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "task_table",
    foreignKeys = [
        ForeignKey(
            entity = SubjectModel::class,
            parentColumns = ["subjectId"],
            childColumns = ["taskSubjectId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("taskSubjectId")]

)
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val taskId :Int=0 ,
    val taskToSubject:String,
    val taskSubjectId :Int =0,
    val taskTitle:String,
    val description :String ="",
    val taskPriority:Int,
    val isCompleted:Boolean,
    val taskDate:String =""
)
