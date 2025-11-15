package com.habiba.studysmart.homeScreen.ui.homeScreenViewModel

import androidx.lifecycle.ViewModel
import com.habiba.studysmart.homeScreen.domain.model.SessionModel
import com.habiba.studysmart.homeScreen.domain.model.SubjectModel
import com.habiba.studysmart.homeScreen.domain.model.TaskModel
import com.habiba.studysmart.homeScreen.util.InputFieldsErrors
import com.habiba.studysmart.homeScreen.util.SubjectsColors
import com.habiba.studysmart.ui.theme.blueGradient
import com.habiba.studysmart.ui.theme.darkBlueGradient
import com.habiba.studysmart.ui.theme.greenGradient
import com.habiba.studysmart.ui.theme.pinkOrangeGradient
import com.habiba.studysmart.ui.theme.purpleGradient
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

            ),
            subjectList = listOf(
                SubjectModel(
                        name = "English",
                        goalHours = 10f,
                        subjectId = 1,
                        subjectColor = greenGradient
                    ),
            SubjectModel(
                name = "arabic",
                goalHours = 10f,
                subjectId = 2,
                subjectColor = pinkOrangeGradient


            ),
            SubjectModel(
                name = "physics",
                goalHours = 10f,
                subjectId = 3,
                subjectColor = darkBlueGradient
            ),
            SubjectModel(
                name = "chemistry",
                goalHours = 10f,
                subjectId = 4,
                subjectColor = purpleGradient
            ),
            SubjectModel(
                name = "maths",
                goalHours = 10f,
                subjectId = 5,
                subjectColor = blueGradient
            ),
                SubjectModel(
                    name = "social study",
                    goalHours = 10f,
                    subjectId = 6,
                    subjectColor = pinkOrangeGradient
                )
        ),
            recentlyStudySessionsList= listOf(
                SessionModel(
                    relatedToSubject = "English",
                    date= "22 oct 2022",
                    duration= 2,
                    sessionSubjectId=1,
                    sessionId= 0 ,

                    ),
        SessionModel(
            relatedToSubject = "arabic",
            date= "22 oct 2022",
            duration= 5,
            sessionSubjectId=2,
            sessionId= 1 ,
        ),
        SessionModel(
            relatedToSubject = "physics",
            date= "22 oct 2022",
            duration= 10,
            sessionSubjectId=3,
            sessionId= 2 ,
        ),
        SessionModel(
            relatedToSubject = "chemistry",
            date= "22 oct 2022",
            duration= 7,
            sessionSubjectId=3,
            sessionId= 3 ,
        ),
        SessionModel(
            relatedToSubject = "maths",
            date= "22 oct 2022",
            duration= 6,
            sessionSubjectId=4,
            sessionId= 4 ,
        ),
        SessionModel(
            relatedToSubject = "social study",
            date= "22 oct 2022",
            duration= 2,
            sessionSubjectId=5,
            sessionId= 5 ,
        ),
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
            is HomeScreenEvents.DeleteSessionClicked -> onDeleteSessionClicked(event.sessionId)
            is HomeScreenEvents.DeleteSessionDialogConfirmed -> deleteSessionDialogConfirmed(homeScreenState.value.currentlySessionDeletedId)
            is HomeScreenEvents.DeleteSessionDialogDismissed -> onDeleteSessionDialogDismissed()
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
            isSubjectNameError = false,
            deleteSessionDialogShowUp = false,
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















