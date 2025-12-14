package com.habiba.studysmart.subjectScreen.domain.usecasesInterface

import com.habiba.studysmart.domain.model.TaskDomainModel

interface IAddNewTaskUseCase {
    suspend operator fun invoke(task: TaskDomainModel)
}