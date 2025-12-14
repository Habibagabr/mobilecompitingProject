package com.habiba.studysmart.homeScreen.ui.homeScreenViewModel

import android.service.autofill.UserData
import com.habiba.studysmart.data.model.SessionModel
import com.habiba.studysmart.data.model.SubjectModel
import com.habiba.studysmart.data.model.TaskModel
import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.domain.model.TaskDomainModel
import com.habiba.studysmart.domain.model.UserDomainModel
import com.habiba.studysmart.domain.model.UserHomeDataDomainModel
import com.habiba.studysmart.homeScreen.util.SubjectsColors

data class HomeScreenState (
    val screenLoaded:Boolean = false,
    val userData: UserHomeDataDomainModel ? = null,
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
    val upComingList:List<TaskDomainModel> = emptyList(),
    val recentlyStudySessionsList:List<SessionDomainModel> = emptyList(),
    val subjectList:List<SubjectDomainModel> = emptyList()
)
