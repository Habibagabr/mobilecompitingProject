package com.habiba.studysmart.homeScreen.ui.homeScreenViewModel

import androidx.lifecycle.ViewModel
import com.habiba.studysmart.homeScreen.domain.model.TaskModel
import com.habiba.studysmart.homeScreen.util.InputFieldsErrors
import com.habiba.studysmart.homeScreen.util.SubjectsColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.collections.listOf


class HomeScreenViewModel(): ViewModel() {
    private val _homeScreenState: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val homeScreenState: StateFlow<HomeScreenState> = _homeScreenState

    init{
        _homeScreenState.value = _homeScreenState.value.copy(
            upComingList = listOf<TaskModel>(
                TaskModel(
                    taskTitle = "meeting the career tasks on the playgaurd",
                    taskPriority = 2,
                    taskDue = "22 oct 2025",
                    isCompleted = true,
                    taskId=6

                ),
                TaskModel(
                    taskTitle = "meeting the career tasks on the playgaurd",
                    taskPriority = 0,
                    taskDue = "22 oct 2025",
                    isCompleted = false,
                    taskId=7

                ),
                TaskModel(
                    taskTitle = "meeting the career tasks on the playgaurd",
                    taskPriority = 2,
                    taskDue = "22 oct 2025",
                    isCompleted = true,
                    taskId=1

                ),
                TaskModel(
                    taskTitle = "meeting the career tasks on the playgaurd",
                    taskPriority = 1,
                    taskDue = "22 oct 2025",
                    isCompleted = true,
                    taskId=2


                ),
                TaskModel(
                    taskTitle = "meeting the career tasks on the playgaurd",
                    taskPriority = 0,
                    taskDue = "22 oct 2025",
                    isCompleted = false,
                    taskId=3


                ),
                TaskModel(
                    taskTitle = "meeting the career tasks on the playgaurd",
                    taskPriority = 2,
                    taskDue = "22 oct 2025",
                    isCompleted = true,
                    taskId=4



                ),
                TaskModel(
                    taskTitle = "meeting the career tasks on the playgaurd",
                    taskPriority = 2,
                    taskDue = "22 oct 2025",
                    isCompleted = true,
                    taskId=5



                ),
                TaskModel(
                    taskTitle = "meeting the career tasks on the playgaurd",
                    taskPriority = 1,
                    taskDue = "22 oct 2025",
                    isCompleted = true,
                    taskId=0



                ),
                TaskModel(
                    taskTitle = "meeting the career tasks on the playgaurd",
                    taskPriority = 2,
                    taskDue = "22 oct 2025",
                    isCompleted = false,
                    taskId=8

                )

            )
        )
    }


    fun homeScreenEventsHandler(event: HomeScreenEvents){
        when(event){
            is HomeScreenEvents.AddNewSubjectBtnClicked -> onAddNewSubjectBtnClicked()
            is HomeScreenEvents.SubjectColorSelected -> onSubjectColorSelected(event.subjectColor)
            is HomeScreenEvents.NewSubjectDialogConfirmed -> onNewSubjectDialogConfirmed(event.subjectName,event.subjectGoalHours)
            is HomeScreenEvents.NewSubjectDialogDismissedOrCanceled -> onNewSubjectDialogDismissedOrCanceled()
            is HomeScreenEvents.SubjectNameFieldChanged -> onSubjectNameFieldChanged(event.newText)
            is HomeScreenEvents.GoalHourFieldChanged -> onGoalHourFieldChanged(event.newText)
            is HomeScreenEvents.TaskCompleted -> onTaskCompleted(event.taskId)
        }
    }

    private fun onTaskCompleted(taskId: Int) {
        _homeScreenState.value = _homeScreenState.value.copy(
            upComingList = _homeScreenState.value.upComingList.map {
                if(it.taskId==taskId)
                    it.copy(isCompleted = !it.isCompleted)
                else
                    it
            }
        )

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
            isSubjectNameError = false

        )



    }

    private fun onNewSubjectDialogConfirmed(subjectName: String, subjectGoalHours: String) {
        val nameError=subjectNameValidation(subjectName)
        val hoursError=subjectGoalHoursValidation(subjectGoalHours)
        _homeScreenState.value = _homeScreenState.value.copy(
            subjectError = nameError,
            goalHourError = hoursError,
            isSubjectNameError = if(nameError==InputFieldsErrors.NoError.errorMsg)false else true,
            isGoalHourError = if(hoursError==InputFieldsErrors.NoError.errorMsg)false else true,

        )





    }

    private fun subjectGoalHoursValidation(subjectGoalHours: String):String {
        return if(subjectGoalHours.isBlank())
            InputFieldsErrors.EmptyGoalHourField.errorMsg
        else if(subjectGoalHours<= 0.toString())
            InputFieldsErrors.InvalidGoalHourField.errorMsg
        else if(subjectGoalHours.any{!it.isDigit()})
            InputFieldsErrors.NoError.errorMsg
        else InputFieldsErrors.NoError.errorMsg


    }

    private fun subjectNameValidation(subjectName: String): String {
        return if(subjectName.isBlank())
            InputFieldsErrors.EmptySubjectNameField.errorMsg
        else if(subjectName.any{!it.isLetterOrDigit()})
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















