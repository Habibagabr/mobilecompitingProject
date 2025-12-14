package com.habiba.studysmart.sessionScreen.domain.usecases.usecasesImplementation

import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.repository.ISessionRepository
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface.IGetUserHistorySessionsUsecase
import javax.inject.Inject

class GetUserHistorySessionsUsecase@Inject constructor(
    private val sessionRepository: ISessionRepository
): IGetUserHistorySessionsUsecase {
    override suspend fun invoke(userId: String): List<SessionDomainModel> {
        return sessionRepository.getUserHistorySessions(userId)
    }
}