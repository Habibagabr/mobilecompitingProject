package com.habiba.studysmart.taskScreen.ui.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habiba.studysmart.domain.model.TaskDomainModel
import com.habiba.studysmart.subjectScreen.domain.usecasesInterface.IAddNewTaskUseCase
import com.habiba.studysmart.taskScreen.PriorityLevels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val addNewTaskUseCase: IAddNewTaskUseCase
) : ViewModel() {
    private val _state : MutableStateFlow<TaskScreenState> = MutableStateFlow(TaskScreenState())
    val state: StateFlow<TaskScreenState> =_state

    val subjectId = savedStateHandle.get<Int>("subjectId")
    val subjectName = savedStateHandle.get<String>("subjectName")


    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: TaskScreenEvents){
        when(event){
            is TaskScreenEvents.TaskTitleChanged -> onTaskTitleChanged(event.taskTitle)
            is TaskScreenEvents.TaskDescriptionChanged -> onTaskDescriptionChanged(event.taskDescription)
            is TaskScreenEvents.DatePickedPressed -> onDatePickerPressed()
            is TaskScreenEvents.CloseDatePicker -> onCloseDatePicker()
            is TaskScreenEvents.DueDateSelected -> onDueDateSelected(event.selectedDate)
            is TaskScreenEvents.PriorityPressed -> onPriorityPressed(event.priorityLevels)
            is TaskScreenEvents.SaveBtnClicked -> onSaveBtnClicked(
                _state.value.taskTitle,
                _state.value.taskDescription,
                _state.value.dueDateSelected,
                _state.value.selectedPriorityLevel
            )

            TaskScreenEvents.TaskAddedSuccessfully -> onTaskAdded()
        }
    }

    private fun onTaskAdded() {
        _state.value=_state.value.copy(
            isTaskAddedSuccessfully = true,
            isDialogShowUp = true
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onSaveBtnClicked(taskTitle: String, taskDescription: String, taskDate: String, taskPriority: PriorityLevels) {
        val validInput=validateTaskInput(taskTitle,taskDescription,taskDate)
        if(validInput){

            // will be removed later
            Log.d("task iew model","$subjectId")

            //save to database
            val priorityLevel =
                when(_state.value.selectedPriorityLevel){
                    PriorityLevels.LOW -> 0
                    PriorityLevels.MEDIUM -> 1
                    PriorityLevels.HIGH -> 2
                }
            val taskModel= TaskDomainModel(
                subjectId = subjectId ?: 0,
                title = taskTitle,
                description = taskDescription,
                priority = priorityLevel,
                isCompleted = false,
                taskDate = taskDate,
                taskToSubject = subjectName?:"",
            )
            viewModelScope.launch {
                addNewTaskUseCase(taskModel)
                _state.value=_state.value.copy(
                    isTaskAddedSuccessfully =true
                )
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateTaskInput(
        taskTitle: String,
        taskDescription: String,
        taskDate: String
    ): Boolean {
        var validInputs = true

        resetErrors()

        if(taskTitle.isBlank()){
            _state.value = _state.value.copy(
                taskTitleError = "Task title can't be empty",
                isTitleValid = false
            )
            validInputs=false
        }

        if(taskDescription.isBlank()){
            _state.value = _state.value.copy(
                taskDescriptionError = "Task Description can't be empty",
                isDescriptionValid = false
            )
            validInputs=false
        }

        if (taskDate.isBlank()) {
            _state.value = _state.value.copy(
                dueDateError = "Please choose a date",
                isDueDateValid = false
            )
            validInputs=false
        } else if (isDateBeforeToday(taskDate)) {
            _state.value = _state.value.copy(
                dueDateError = "Task date can't be in the past",
                isDueDateValid = false
            )
            validInputs=false
        }
        return validInputs
    }

    private fun resetErrors() {
        _state.value=_state.value.copy(
            taskTitleError = "",
            isTitleValid = true,
            taskDescriptionError = "",
            isDescriptionValid = true,
            isDueDateValid = true,
            dueDateError = ""
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun isDateBeforeToday(taskDateString: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("dd - MM - yyyy")
        val taskDate = LocalDate.parse(taskDateString, formatter)
        val today = LocalDate.now()

        return taskDate.isBefore(today)
    }


    private fun onPriorityPressed(priorityLevels: PriorityLevels) {
        _state.value=_state.value.copy(
            selectedPriorityLevel =priorityLevels
        )
    }

    private fun onDueDateSelected(selectedDate:String) {
        _state.value=_state.value.copy(
            dueDateSelected=selectedDate,
            isDatePickerDialogOpened = false
        )

    }


    private fun onCloseDatePicker(){
        _state.value=_state.value.copy(
            isDatePickerDialogOpened = false
        )
    }

    private fun onTaskTitleChanged(taskTitle: String) {
        _state.value=_state.value.copy(
            taskTitle=taskTitle
        )

    }

    private fun onTaskDescriptionChanged(taskDescription: String) {
        _state.value=_state.value.copy(
            taskDescription=taskDescription
        )

    }

    private fun onDatePickerPressed() {
        _state.value=_state.value.copy(
            isDatePickerDialogOpened = true
        )

    }

}