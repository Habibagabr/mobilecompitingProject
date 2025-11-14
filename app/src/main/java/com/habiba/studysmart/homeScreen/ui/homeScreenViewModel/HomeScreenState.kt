package com.habiba.studysmart.homeScreen.ui.homeScreenViewModel

import com.habiba.studysmart.homeScreen.domain.model.TaskModel
import com.habiba.studysmart.homeScreen.util.SubjectsColors

data class HomeScreenState (
    val subjectDialogShowUp : Boolean = false,
    val colorSelected : SubjectsColors = SubjectsColors.PurpleGradient,
    val subjectName : String = "",
    val subjectGoalHours : String = "",
    val subjectError: String = "",
    val goalHourError: String = "",
    val isSubjectNameError:Boolean = false,
    val isGoalHourError:Boolean = false,
    val upComingList:List<TaskModel> = emptyList()
)
