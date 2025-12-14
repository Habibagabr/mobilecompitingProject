package com.habiba.studysmart.taskScreen.ui.viewModel

import com.habiba.studysmart.taskScreen.PriorityLevels

data class TaskScreenState (
    val taskTitle:String="",
    val taskDescription:String="",
    val taskTitleError:String="",
    val taskDescriptionError: String="",
    val isTitleValid:Boolean = true ,
    val isDescriptionValid:Boolean = true ,

    val dueDateSelected:String="",
    val isDatePickerDialogOpened: Boolean = false,

    val selectedPriorityLevel : PriorityLevels = PriorityLevels.LOW,

    val dueDateError:String="",
    val isDueDateValid:Boolean=true,

    val isDialogShowUp:Boolean=false,
    val isTaskAddedSuccessfully:Boolean=false

)