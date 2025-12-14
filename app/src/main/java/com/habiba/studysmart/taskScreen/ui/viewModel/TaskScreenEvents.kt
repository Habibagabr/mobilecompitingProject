package com.habiba.studysmart.taskScreen.ui.viewModel

import com.habiba.studysmart.domain.model.TaskDomainModel
import com.habiba.studysmart.taskScreen.PriorityLevels

sealed class TaskScreenEvents {
    data class TaskTitleChanged(val taskTitle:String): TaskScreenEvents()
    data class TaskDescriptionChanged(val taskDescription:String): TaskScreenEvents()

    data object DatePickedPressed: TaskScreenEvents()
    data object CloseDatePicker:TaskScreenEvents()
    data class DueDateSelected(val selectedDate:String): TaskScreenEvents()

    data class PriorityPressed(val priorityLevels: PriorityLevels): TaskScreenEvents()

    data object SaveBtnClicked: TaskScreenEvents()

    data object TaskAddedSuccessfully: TaskScreenEvents()

}