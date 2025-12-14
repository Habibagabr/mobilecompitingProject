package com.habiba.studysmart.data.utils.localMappers

import com.habiba.studysmart.data.model.SessionModel
import com.habiba.studysmart.data.model.SubjectIdNameModel
import com.habiba.studysmart.data.model.SubjectModel
import com.habiba.studysmart.data.model.SubjectWithTasksAndSessions
import com.habiba.studysmart.data.model.TaskModel
import com.habiba.studysmart.data.model.UserHomeDataModel
import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.model.SubjectDetailsDomainModel
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.model.SubjectIdNameDomainModel
import com.habiba.studysmart.domain.model.TaskDomainModel
import com.habiba.studysmart.domain.model.UserDomainModel
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel
import com.habiba.studysmart.homeScreen.util.SubjectsColors

fun UserModel.toDomain(): UserDomainModel {
    return UserDomainModel(
        userId = userId,
        name = userName,
        subjectCount = subjectCount,
        studiedHours = studiedHour,
        studyGoalHour = studyGoalHour,
        userEmail=userEmail,
        userPassword= userPassword,

    )
}

fun SubjectModel.toDomain(): SubjectDomainModel {
    return SubjectDomainModel(
        id = this.subjectId,
        userOwnerId = this.subjectUserOwnerId,
        name = this.subjectName,
        goalHours = this.goalHours,
        actualHours = this.actualHours,
         colorHex = SubjectsColors.valueOf(this.subjectColor)
    )
}

fun TaskModel.toDomain(): TaskDomainModel {
    return TaskDomainModel(
        taskId = taskId,
        subjectId = taskSubjectId,
        title = taskTitle,
        description = description,
        priority = taskPriority,
        isCompleted = isCompleted,
        taskDate = taskDate,
        taskToSubject = taskToSubject
    )
}

fun SessionModel.toDomain(): SessionDomainModel{
      return SessionDomainModel(
        sessionId = sessionId,
        subjectId = sessionSubjectId,
        relatedToSubject = relatedToSubject,
        date = date,
        duration = duration
    )
}

fun SubjectWithTasksAndSessions.toDomain(): SubjectDetailsDomainModel{
    return SubjectDetailsDomainModel(
        subject= subject.toDomain(),
        tasks = subjectTasks.map{it.toDomain()},
        sessions = subjectSessions.map{it.toDomain()}

    )
}

fun UserHomeDataModel.toDomain(): UserHomeDataDomainModel {
    return UserHomeDataDomainModel(
        user = user.toDomain(),
        details = userDetails.map { it.toDomain() }
    )
}

fun SubjectIdNameModel.toDomain(): SubjectIdNameDomainModel{
    return SubjectIdNameDomainModel(
        subjectId = subjectId,
        subjectName=subjectName
    )
}






