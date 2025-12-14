package com.habiba.studysmart.subjectScreen.domain.usecasesImplementation

import com.habiba.studysmart.domain.model.TaskDomainModel
import com.habiba.studysmart.domain.repository.ISubjectRepository
import com.habiba.studysmart.subjectScreen.domain.usecasesInterface.IAddNewTaskUseCase
import javax.inject.Inject

class AddNewTaskUseCase@Inject constructor(
    private val subjectRepository: ISubjectRepository

): IAddNewTaskUseCase {
    override suspend fun invoke(task: TaskDomainModel) {
        subjectRepository.addNewTask(task)

    }
}