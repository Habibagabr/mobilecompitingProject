package com.habiba.studysmart.sessionScreen.domain.usecases.usecasesImplementation

import com.habiba.studysmart.domain.model.SubjectIdNameDomainModel
import com.habiba.studysmart.domain.repository.ISessionRepository
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface.IGetUserUniqueSubjectsUsecase
import javax.inject.Inject

class GetUserUniqueSubjectsUsecase@Inject constructor(
    private val sessionRepository : ISessionRepository
): IGetUserUniqueSubjectsUsecase {
    override suspend fun invoke(userId: String): List<SubjectIdNameDomainModel> {
        return sessionRepository.getUserUniqueSubjects(userId)
    }

}