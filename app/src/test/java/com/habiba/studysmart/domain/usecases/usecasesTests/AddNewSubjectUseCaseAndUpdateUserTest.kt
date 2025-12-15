package com.habiba.studysmart.homeScreen.domain.usecases.usecasesTests

import com.habiba.studysmart.data.model.UserHomeDataModel
import com.habiba.studysmart.data.model.UserModel
import com.habiba.studysmart.data.utils.localMappers.toDomain
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.repository.IHomeRepository
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.AddNewSubjectUseCaseAndUpdateUser
import com.habiba.studysmart.homeScreen.util.SubjectsColors
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AddNewSubjectUseCaseAndUpdateUserTest {

    private val homeRepository: IHomeRepository = mock()
    private lateinit var useCase: AddNewSubjectUseCaseAndUpdateUser

    @Before
    fun setup() {
        useCase = AddNewSubjectUseCaseAndUpdateUser(homeRepository)
    }

    @Test
    fun `invoke adds subject and returns updated user home data`() = runTest {

        // Arrange
        val fakeSubject = SubjectDomainModel(
            id = null,
            userOwnerId = "1",
            name = "Math",
            goalHours = 20,
            actualHours = 5,
            colorHex = SubjectsColors.BlueGradient
        )

        val fakeUserDataModel = UserModel(
            userId = "1",
            userEmail = "test@test.com",
            userPassword = "123",
            userName = "Habiba",
            subjectCount = 1,
            studiedHour = 0,
            studyGoalHour = 10
        )

        val fakeHomeDataModel = UserHomeDataModel(
            user = fakeUserDataModel,
            userDetails = emptyList()
        )

        whenever(homeRepository.addSubjectAndUpdateUser(fakeSubject))
            .thenReturn(fakeHomeDataModel)

        // Act
        val result = useCase.invoke(fakeSubject)

        // Assert
        verify(homeRepository).addSubjectAndUpdateUser(fakeSubject)

        assertEquals(
            fakeHomeDataModel.toDomain(),
            result
        )
    }
}
