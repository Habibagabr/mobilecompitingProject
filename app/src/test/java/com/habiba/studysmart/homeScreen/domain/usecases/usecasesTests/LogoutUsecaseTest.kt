package com.habiba.studysmart.homeScreen.domain.usecases.usecasesTests

import com.habiba.studysmart.domain.repository.IAuthenticationRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.LogoutUsecase
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class LogoutUsecaseTest {

    private val authenticationRepository: IAuthenticationRepository = mock()
    private lateinit var useCase: LogoutUsecase

    @Before
    fun setup() {
        useCase = LogoutUsecase(authenticationRepository)
    }

    @Test
    fun `invoke calls logout on authentication repository`() {

        // Act
        useCase.invoke()

        // Assert
        verify(authenticationRepository).logout()
    }
}
