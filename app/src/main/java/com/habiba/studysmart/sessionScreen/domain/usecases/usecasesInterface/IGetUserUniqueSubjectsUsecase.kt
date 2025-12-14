package com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface

import com.habiba.studysmart.domain.model.SubjectIdNameDomainModel

interface IGetUserUniqueSubjectsUsecase {
    suspend operator fun invoke(userId:String):List<SubjectIdNameDomainModel>
}