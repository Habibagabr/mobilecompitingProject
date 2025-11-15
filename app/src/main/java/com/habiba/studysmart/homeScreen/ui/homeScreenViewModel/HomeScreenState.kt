package com.habiba.studysmart.homeScreen.ui.homeScreenViewModel

import com.habiba.studysmart.homeScreen.domain.model.SessionModel
import com.habiba.studysmart.homeScreen.domain.model.SubjectModel
import com.habiba.studysmart.homeScreen.domain.model.TaskModel
import com.habiba.studysmart.homeScreen.util.SubjectsColors

data class HomeScreenState (
    val subjectDialogShowUp : Boolean = false,
    val deleteSessionDialogShowUp : Boolean = false,
    val isDeleteSessionDialogConfirmed : Boolean = false,
    val currentlySessionDeletedId : Int =0,
    val colorSelected : SubjectsColors = SubjectsColors.PurpleGradient,
    val subjectName : String = "",
    val subjectGoalHours : String = "",
    val subjectError: String = "",
    val goalHourError: String = "",
    val isSubjectNameError:Boolean = false,
    val isGoalHourError:Boolean = false,
    val upComingList:List<TaskModel> = emptyList(),
    val recentlyStudySessionsList:List<SessionModel> = emptyList(),
    val subjectList:List<SubjectModel> = emptyList()
)
