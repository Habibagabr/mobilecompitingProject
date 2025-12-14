package com.habiba.studysmart.subjectScreen.domain.usecasesInterface

import com.habiba.studysmart.domain.model.SubjectDetailsDomainModel

interface IGetSubjectDetailsUseCase {
    suspend operator fun invoke(subjectId:Int) : SubjectDetailsDomainModel
}