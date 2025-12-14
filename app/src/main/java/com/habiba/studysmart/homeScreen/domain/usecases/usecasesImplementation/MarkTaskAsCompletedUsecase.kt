package com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation

import com.habiba.studysmart.domain.repository.IHomeRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IMarkTaskAsCompletedUseCase
import javax.inject.Inject

class MarkTaskAsCompletedUsecase@Inject constructor(
    private val homeRepository: IHomeRepository
): IMarkTaskAsCompletedUseCase
{
    override suspend fun invoke(taskId: Int) {
        homeRepository.markTaskCompleted(taskId)
    }
}