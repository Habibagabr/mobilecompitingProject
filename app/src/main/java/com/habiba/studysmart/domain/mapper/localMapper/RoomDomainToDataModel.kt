package com.habiba.studysmart.domain.mapper.localMapper

import com.habiba.studysmart.data.model.SessionModel
import com.habiba.studysmart.data.model.SubjectModel
import com.habiba.studysmart.data.model.TaskModel
import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.model.SubjectDetailsDomainModel
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.model.TaskDomainModel
import com.habiba.studysmart.domain.model.UserDomainModel
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel

fun SubjectDomainModel.toData(): SubjectModel {
    return SubjectModel(
        subjectUserOwnerId = this.userOwnerId,
        subjectName = this.name,
        goalHours = this.goalHours,
        actualHours = this.actualHours,
        subjectColor = this.colorHex.name
    )
}


fun TaskDomainModel.toData(): TaskModel {
    return TaskModel(
        taskSubjectId = this.subjectId,
        taskTitle = this.title,
        description = this.description,
        taskPriority = this.priority,
        isCompleted = this.isCompleted,
        taskDate = this.taskDate,
        taskToSubject = this.taskToSubject
    )
}

fun SessionDomainModel.toData(): SessionModel {
    return SessionModel(
        sessionId = this.sessionId?:0,
        sessionSubjectId = this.subjectId,
        relatedToSubject = this.relatedToSubject,
        date = this.date,
        duration = this.duration
    )
}

fun UserDomainModel.toData(): UserModel {
    return UserModel(
        userId = this.userId,
        userName = this.name,
        subjectCount = this.subjectCount,
        studiedHour = this.studiedHours,
        studyGoalHour = this.studyGoalHour,
        userEmail = this.userEmail,
        userPassword = this.userPassword
    )
}


fun SubjectDetailsDomainModel.toData():
        Triple<SubjectModel?, List<TaskModel>?, List<SessionModel>?> {

    return Triple(
        subject?.toData() ,
        tasks?.map { it.toData() }?:emptyList(),
        sessions?.map { it.toData() } ?: emptyList()
    )
}



fun UserHomeDataDomainModel.toData():
        Pair<UserModel, List<Triple<SubjectModel, List<TaskModel>, List<SessionModel>>>> {

    return Pair(
        first = user?.toData() ?: null,
        second = details?.map { (subject, tasks, sessions) ->
            Triple(
                subject?.toData() ?: SubjectModel(
                    subjectId = 1,
                    subjectUserOwnerId = "",
                    subjectName = "",
                    goalHours = 0,
                    actualHours = 0,
                    subjectColor = "0xFFFFFFFF"
                ),
                tasks?.map { it.toData() } ?: emptyList(),
                sessions?.map { it.toData() } ?: emptyList()
            )
        } ?: emptyList()
    ) as Pair<UserModel, List<Triple<SubjectModel, List<TaskModel>, List<SessionModel>>>>
}




