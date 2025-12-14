package com.habiba.studysmart.sessionScreen.domain.usecases.usecasesImplementation

import com.habiba.studysmart.domain.mapper.localMapper.toData
import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.repository.ISessionRepository
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface.ISaveNewSessionUsecase
import javax.inject.Inject

class SaveNewSessionUsecase@Inject constructor(
    private val sessionRepository: ISessionRepository
): ISaveNewSessionUsecase {
    override suspend fun invoke(session: SessionDomainModel) {
        sessionRepository.saveNewSession(session.toData())
    }

}