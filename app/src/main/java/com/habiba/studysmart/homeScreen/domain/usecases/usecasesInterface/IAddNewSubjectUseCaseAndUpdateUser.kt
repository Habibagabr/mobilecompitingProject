package com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface

import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel

interface IAddNewSubjectUseCaseAndUpdateUser {
    suspend operator fun invoke(subject: SubjectDomainModel):UserHomeDataDomainModel
}