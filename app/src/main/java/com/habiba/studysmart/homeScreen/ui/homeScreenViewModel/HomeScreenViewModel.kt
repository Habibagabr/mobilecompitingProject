package com.habiba.studysmart.homeScreen.ui.homeScreenViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habiba.studysmart.data.model.UserHomeDataModel
import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesImplementation.MarkTaskAsCompletedUsecase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IAddNewSubjectUseCaseAndUpdateUser
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IGetUserHomeData
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IGetUserIdUsecase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.ILogoutUsecase
import com.habiba.studysmart.homeScreen.domain.usecases.usecasesInterface.IMarkTaskAsCompletedUseCase
import com.habiba.studysmart.homeScreen.util.InputFieldsErrors
import com.habiba.studysmart.homeScreen.util.SubjectsColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.collections.emptyList

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeScreenViewModel@Inject constructor(
    private val getUserIdUseCase: IGetUserIdUsecase,
    private val getUserData: IGetUserHomeData,
    private val addNewSubjectAndUpdateUser : IAddNewSubjectUseCaseAndUpdateUser,
    private val markTaskAsCompletedUsecase: IMarkTaskAsCompletedUseCase,
    private val logoutUsecase: ILogoutUsecase
): ViewModel() {
    private val _homeScreenState: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val homeScreenState: StateFlow<HomeScreenState> = _homeScreenState

    init{
        onEvent(HomeScreenEvents.ScreenLoading())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: HomeScreenEvents){
        when(event){
            is HomeScreenEvents.AddNewSubjectBtnClicked -> onAddNewSubjectBtnClicked()
            is HomeScreenEvents.SubjectColorSelected -> onSubjectColorSelected(event.subjectColor)
            is HomeScreenEvents.NewSubjectDialogConfirmed -> onNewSubjectDialogConfirmed(event.subjectName,event.subjectGoalHours , event.colorSelected)
            is HomeScreenEvents.NewSubjectDialogDismissedOrCanceled -> onNewSubjectDialogDismissedOrCanceled()
            is HomeScreenEvents.SubjectNameFieldChanged -> onSubjectNameFieldChanged(event.newText)
            is HomeScreenEvents.GoalHourFieldChanged -> onGoalHourFieldChanged(event.newText)
            is HomeScreenEvents.TaskCompleted -> onTaskCompleted(event.taskId)
            is HomeScreenEvents.DeleteSessionClicked -> onDeleteSessionClicked(event.sessionId)
            is HomeScreenEvents.DeleteSessionDialogConfirmed -> deleteSessionDialogConfirmed(homeScreenState.value.currentlySessionDeletedId)
            is HomeScreenEvents.DeleteSessionDialogDismissed -> onDeleteSessionDialogDismissed()
            is HomeScreenEvents.ScreenLoading -> onScreenLoaded()
            is HomeScreenEvents.ValidSubject -> addNewSubject(event.subject)
            is HomeScreenEvents.LogoutConfirmed -> onLogoutConfirmed()
        }
    }

    private fun onLogoutConfirmed() {
        logoutUsecase()

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun onScreenLoaded() {

        viewModelScope.launch {

            val userId = getUserIdUseCase()
            if (userId == null || userId.isBlank()) {
                Log.e("HomeVM", "Error: userId is null or empty")
            }

            Log.d("HomeVM", "Loading data for $userId")

            val userData = getUserData(userId)

            Log.d("HomeVM", "User data loaded successfully")

            val subjects = userData.details?.mapNotNull { it.subject }.orEmpty()
            val formatter = DateTimeFormatter.ofPattern("dd - MM - yyyy")
            val today = LocalDate.now()

            val upcomingTasks = userData.details
                ?.flatMap { subject ->
                    subject.tasks.orEmpty().filter { task ->
                        val date = LocalDate.parse(task.taskDate, formatter)

                        !task.isCompleted && (date.isAfter(today) || date.isEqual(today))
                    }
                }
                .orEmpty()

            val sessions: List<SessionDomainModel> =
                userData.details
                    ?.flatMap { it.sessions.orEmpty() }
                    ?: emptyList()


            _homeScreenState.value = _homeScreenState.value.copy(
                userData = userData,
                screenLoaded = true,
                subjectList = subjects,
                upComingList = upcomingTasks,
                recentlyStudySessionsList = sessions
            )
        }
    }

    private fun onDeleteSessionDialogDismissed() {
        _homeScreenState.value = _homeScreenState.value.copy(
            deleteSessionDialogShowUp = false,
            isDeleteSessionDialogConfirmed = false
        )
    }


    private fun onDeleteSessionClicked(sessionId: Int) {
        _homeScreenState.value = _homeScreenState.value.copy(
            deleteSessionDialogShowUp = true,
            currentlySessionDeletedId = sessionId
        )
    }

    private fun deleteSessionDialogConfirmed(sessionId: Int) {
        _homeScreenState.value = _homeScreenState.value.copy(
            isDeleteSessionDialogConfirmed = true,
            deleteSessionDialogShowUp = false,
            recentlyStudySessionsList =  _homeScreenState.value.recentlyStudySessionsList.filter { it.sessionId!=sessionId }
        )

    }

    private fun onTaskCompleted(taskId: Int) {
        viewModelScope.launch {
            markTaskAsCompletedUsecase(taskId)
            _homeScreenState.value = _homeScreenState.value.copy(
                upComingList = _homeScreenState.value.upComingList.map {
                    if(it.taskId==taskId)
                        it.copy(isCompleted = !it.isCompleted)
                    else
                        it
                }
            )
        }

    }

    private fun onAddNewSubjectBtnClicked(){
        _homeScreenState.value =_homeScreenState.value.copy(
            subjectDialogShowUp = true
        )
    }

    private fun onSubjectColorSelected(subjectColor: SubjectsColors) {
        _homeScreenState.value = _homeScreenState.value.copy(
            colorSelected = subjectColor
        )

    }
    private fun onNewSubjectDialogDismissedOrCanceled() {
        _homeScreenState.value =_homeScreenState.value.copy(
            subjectDialogShowUp = false,
            subjectName = "",
            colorSelected = SubjectsColors.PurpleGradient,
            subjectGoalHours = "",
            subjectError = InputFieldsErrors.NoError.errorMsg,
            goalHourError = InputFieldsErrors.NoError.errorMsg,
            isGoalHourError = false,
            isSubjectNameError = false,
            deleteSessionDialogShowUp = false,
        )

    }

    private fun onNewSubjectDialogConfirmed(subjectName: String, subjectGoalHours: String , chosenColors: SubjectsColors) {

        val nameError = subjectNameValidation(subjectName)
        val hoursError = subjectGoalHoursValidation(subjectGoalHours)

        _homeScreenState.value = _homeScreenState.value.copy(
            subjectError = nameError,
            goalHourError = hoursError,
            isSubjectNameError = nameError != InputFieldsErrors.NoError.errorMsg,
            isGoalHourError = hoursError != InputFieldsErrors.NoError.errorMsg,
            subjectName = subjectName,
            subjectGoalHours = subjectGoalHours
        )

        if (!_homeScreenState.value.isSubjectNameError &&
            !_homeScreenState.value.isGoalHourError
        ) {
            val subject = SubjectDomainModel(
                name = subjectName,
                goalHours = subjectGoalHours.toInt(),
                userOwnerId = getUserIdUseCase()?:"",
                actualHours = 0,
                colorHex = _homeScreenState.value.colorSelected
            )

            onEvent(HomeScreenEvents.ValidSubject(subject))
        }
    }

    fun addNewSubject(subject: SubjectDomainModel) {
        viewModelScope.launch {
            val updatedUserData = addNewSubjectAndUpdateUser(subject)

            val subjects = updatedUserData.details?.mapNotNull { it.subject }.orEmpty()

            _homeScreenState.value = _homeScreenState.value.copy(
                userData = updatedUserData,
                subjectDialogShowUp = false,
                subjectName = "",
                subjectGoalHours = "",
                screenLoaded = true,
                subjectList = subjects,


            )
        }
    }

    private fun subjectGoalHoursValidation(subjectGoalHours: String):String {
        return if(subjectGoalHours.isBlank())
            InputFieldsErrors.EmptyGoalHourField.errorMsg
        else if(subjectGoalHours<= 0.toString())
            InputFieldsErrors.InvalidGoalNegativeHourField.errorMsg
        else if(subjectGoalHours<1.toString())
            InputFieldsErrors.InvalidGoalSmallHourField.errorMsg
        else if(subjectGoalHours.any{!it.isDigit()})
            InputFieldsErrors.NoError.errorMsg
        else InputFieldsErrors.NoError.errorMsg


    }

    private fun subjectNameValidation(subjectName: String): String {
        return if(subjectName.isBlank())
            InputFieldsErrors.EmptySubjectNameField.errorMsg
        else if(subjectName.any { !(it.isLetterOrDigit() || it.isWhitespace()) })
            InputFieldsErrors.InvalidSubjectName.errorMsg
        else
            InputFieldsErrors.NoError.errorMsg

    }

    private fun onSubjectNameFieldChanged(newText: String) {
        _homeScreenState.value = _homeScreenState.value.copy(
            subjectName = newText
        )

    }

    private fun onGoalHourFieldChanged(newText: String) {
        _homeScreenState.value = _homeScreenState.value.copy(
            subjectGoalHours = newText
        )

    }


}















