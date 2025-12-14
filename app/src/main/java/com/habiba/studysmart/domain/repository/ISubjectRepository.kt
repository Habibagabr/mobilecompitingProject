package com.habiba.studysmart.domain.repository

import com.habiba.studysmart.domain.model.SubjectDetailsDomainModel
import com.habiba.studysmart.domain.model.TaskDomainModel

interface ISubjectRepository {
    suspend fun getSubjectDetails(subjectId: Int): SubjectDetailsDomainModel
    suspend fun addNewTask(taskDomainModel: TaskDomainModel)
}