package com.habiba.studysmart.homeScreen.domain.usecases.usecasesTests

import com.habiba.studysmart.domain.repository.IHomeRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.MarkTaskAsCompletedUsecase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MarkTaskAsCompletedUsecaseTest {

    private val homeRepository: IHomeRepository = mock()
    private lateinit var useCase: MarkTaskAsCompletedUsecase

    @Before
    fun setup() {
        useCase = MarkTaskAsCompletedUsecase(homeRepository)
    }

    @Test
    fun `invoke marks task as completed in repository`() = runTest {

        // Arrange
        val taskId = 10

        // Act
        useCase.invoke(taskId)

        // Assert
        verify(homeRepository).markTaskCompleted(taskId)
    }
}
