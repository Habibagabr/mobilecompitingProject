package com.habiba.studysmart.domain.repository

import com.habiba.studysmart.data.model.SessionModel
import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.model.SubjectIdNameDomainModel

interface ISessionRepository {

    suspend fun getUserHistorySessions(
        userId: String
    ): List<SessionDomainModel>

    suspend fun getUserUniqueSubjects(
        userId: String
    ): List<SubjectIdNameDomainModel>

    suspend fun saveNewSession(session: SessionModel)
}
