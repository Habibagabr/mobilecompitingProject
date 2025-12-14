package com.habiba.studysmart.subjectScreen.domain.usecasesImplementation

import com.habiba.studysmart.domain.model.SubjectDetailsDomainModel
import com.habiba.studysmart.domain.repository.ISubjectRepository
import com.habiba.studysmart.subjectScreen.domain.usecasesInterface.IGetSubjectDetailsUseCase
import javax.inject.Inject

class GetSubjectDetailsUseCase@Inject constructor(
    private val subjectRepository : ISubjectRepository

): IGetSubjectDetailsUseCase {

    override suspend fun invoke(subjectId: Int): SubjectDetailsDomainModel {
       return subjectRepository.getSubjectDetails(subjectId)
    }
}