package com.habiba.studysmart.homeScreen.domain.usecases.usecasesTests

import com.habiba.studysmart.domain.model.SubjectDetailsDomainModel
import com.habiba.studysmart.domain.model.UserDomainModel
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel
import com.habiba.studysmart.domain.repository.IHomeRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.GetUserHomeDataUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetUserHomeDataUseCaseTest {

    private val homeRepository: IHomeRepository = mock()
    private lateinit var useCase: GetUserHomeDataUseCase

    @Before
    fun setup() {
        useCase = GetUserHomeDataUseCase(homeRepository)
    }

    @Test
    fun `invoke returns user home data from repository`() = runTest {

        // Arrange
        val fakeUserId = "1"

        val fakeUser = UserDomainModel(
            userId = fakeUserId,
            userEmail = "habiba@test.com",
            userPassword = "123456",
            name = "Habiba",
            subjectCount = 3,
            studiedHours = 20,
            studyGoalHour = 50
        )

        val fakeSubjectDetails = SubjectDetailsDomainModel(
            subject = null,
            tasks = emptyList(),
            sessions = emptyList()
        )

        val fakeHomeData = UserHomeDataDomainModel(
            user = fakeUser,
            details = listOf(fakeSubjectDetails)
        )

        whenever(homeRepository.getUserData(fakeUserId))
            .thenReturn(fakeHomeData)

        // Act
        val result = useCase.invoke(fakeUserId)

        // Assert
        assertEquals(fakeHomeData, result)
    }
}
