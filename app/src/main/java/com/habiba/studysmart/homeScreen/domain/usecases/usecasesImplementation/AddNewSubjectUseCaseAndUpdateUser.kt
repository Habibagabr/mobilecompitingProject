package com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation

import com.habiba.studysmart.data.utils.localMappers.toDomain
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel
import com.habiba.studysmart.domain.repository.IHomeRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IAddNewSubjectUseCaseAndUpdateUser
import javax.inject.Inject

class AddNewSubjectUseCaseAndUpdateUser@Inject constructor(
    private val homeRepository: IHomeRepository
): IAddNewSubjectUseCaseAndUpdateUser {
    override suspend operator fun invoke(subject: SubjectDomainModel): UserHomeDataDomainModel {
        return homeRepository.addSubjectAndUpdateUser(subject).toDomain()
    }
}