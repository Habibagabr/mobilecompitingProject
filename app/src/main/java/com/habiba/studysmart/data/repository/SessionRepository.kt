package com.habiba.studysmart.data.repository

import com.habiba.studysmart.data.dataSource.local.database.IDatabaseServices
import com.habiba.studysmart.data.model.SessionModel
import com.habiba.studysmart.data.model.SubjectIdNameModel
import com.habiba.studysmart.data.utils.localMappers.toDomain
import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.model.SubjectIdNameDomainModel
import com.habiba.studysmart.domain.repository.ISessionRepository
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val dataBaseService: IDatabaseServices
) : ISessionRepository {

    override suspend fun getUserHistorySessions(
        userId: String
    ): List<SessionDomainModel> {
        return dataBaseService
            .getUserHistorySession(userId)
            .map { it.toDomain() }
    }

    override suspend fun getUserUniqueSubjects(
        userId: String
    ): List<SubjectIdNameDomainModel> {
        return dataBaseService.getAllUserUniqueSubjects(userId).map { item -> item.toDomain() }
    }

    override suspend fun saveNewSession(session: SessionModel) {
        dataBaseService.saveSession(session)
    }
}
