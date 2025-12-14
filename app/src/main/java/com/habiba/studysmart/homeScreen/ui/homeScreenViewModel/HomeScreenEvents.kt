package com.habiba.studysmart.homeScreen.ui.homeScreenViewModel

import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.homeScreen.util.SubjectsColors

sealed class HomeScreenEvents() {
    class ScreenLoading(): HomeScreenEvents()
    class AddNewSubjectBtnClicked(): HomeScreenEvents()
    class DeleteSessionClicked(val sessionId:Int): HomeScreenEvents()
    class SubjectColorSelected(val subjectColor: SubjectsColors): HomeScreenEvents()
    class NewSubjectDialogDismissedOrCanceled() : HomeScreenEvents()

    class NewSubjectDialogConfirmed(
        val subjectName: String,
        val subjectGoalHours: String,
        val colorSelected: SubjectsColors
    ): HomeScreenEvents()

    class SubjectNameFieldChanged(val newText:String): HomeScreenEvents()
    class GoalHourFieldChanged(val newText:String): HomeScreenEvents()
    class TaskCompleted(val taskId:Int): HomeScreenEvents()
    class DeleteSessionDialogConfirmed() : HomeScreenEvents()
    class DeleteSessionDialogDismissed : HomeScreenEvents()
    class ValidSubject(val subject : SubjectDomainModel): HomeScreenEvents()

    class LogoutConfirmed(): HomeScreenEvents()


}