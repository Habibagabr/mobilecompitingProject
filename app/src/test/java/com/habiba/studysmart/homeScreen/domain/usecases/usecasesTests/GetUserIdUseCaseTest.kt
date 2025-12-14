package com.habiba.studysmart.homeScreen.domain.usecases.usecasesTests

import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.GetUserIdUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetUserIdUseCaseTest {

    private val authenticationRepository: IAuthenticationRepository = mock()

    private lateinit var useCase: GetUserIdUseCase

    @Before
    fun setup() {
        useCase = GetUserIdUseCase(authenticationRepository)
    }

    @Test
    fun `invoke returns user id when repository returns value`() {
        // Arrange
        whenever(authenticationRepository.getUserId())
            .thenReturn("123")

        // Act
        val result = useCase.invoke()

        // Assert
        assertEquals("123", result)
    }

    @Test
    fun `invoke returns default id when repository returns null`() {
        // Arrange
        whenever(authenticationRepository.getUserId())
            .thenReturn(null)

        // Act
        val result = useCase.invoke()

        // Assert
        assertEquals("1", result)
    }
}
