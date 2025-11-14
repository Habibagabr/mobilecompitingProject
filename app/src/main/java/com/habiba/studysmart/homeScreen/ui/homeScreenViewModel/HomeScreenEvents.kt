package com.habiba.studysmart.homeScreen.ui.homeScreenViewModel

import com.habiba.studysmart.homeScreen.util.SubjectsColors

sealed class HomeScreenEvents() {
    class AddNewSubjectBtnClicked(): HomeScreenEvents()
    class SubjectColorSelected(val subjectColor: SubjectsColors): HomeScreenEvents()
    class NewSubjectDialogDismissedOrCanceled() : HomeScreenEvents()
    class NewSubjectDialogConfirmed(val subjectName:String , val subjectGoalHours:String): HomeScreenEvents()
    class SubjectNameFieldChanged(val newText:String): HomeScreenEvents()
    class GoalHourFieldChanged(val newText:String): HomeScreenEvents()
    class TaskCompleted(val taskId:Int): HomeScreenEvents()

}