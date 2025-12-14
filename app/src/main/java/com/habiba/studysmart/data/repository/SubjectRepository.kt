package com.habiba.studysmart.data.repository

import com.habiba.studysmart.data.dataSource.local.database.DatabaseServices
import com.habiba.studysmart.data.dataSource.local.database.IDatabaseServices
import com.habiba.studysmart.data.utils.localMappers.toDomain
import com.habiba.studysmart.domain.mapper.localMapper.toData
import com.habiba.studysmart.domain.model.SubjectDetailsDomainModel
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.model.TaskDomainModel
import com.habiba.studysmart.domain.repository.ISubjectRepository
import javax.inject.Inject

class SubjectRepository@Inject constructor(
    private val databaseServices: IDatabaseServices
): ISubjectRepository {

    override suspend fun getSubjectDetails(subjectId: Int): SubjectDetailsDomainModel {
        val subjectDetailsData = databaseServices.getSubjectDetails(subjectId)
        return subjectDetailsData.toDomain()
    }

    override suspend fun addNewTask(taskDomainModel: TaskDomainModel) {
        databaseServices.addNewTask(taskDomainModel.toData())
    }
}