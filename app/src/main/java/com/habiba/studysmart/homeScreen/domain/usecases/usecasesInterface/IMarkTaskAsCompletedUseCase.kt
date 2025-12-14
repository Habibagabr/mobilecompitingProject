package com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface

interface IMarkTaskAsCompletedUseCase {
    suspend operator fun invoke(taskId: Int)
}