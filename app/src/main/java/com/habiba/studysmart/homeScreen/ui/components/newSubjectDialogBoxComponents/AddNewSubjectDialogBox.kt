package com.habiba.studysmart.homeScreen.ui.components.newSubjectDialogBoxComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.habiba.studysmart.R
import com.habiba.studysmart.commonUi.components.PopupDialog
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenState
import com.habiba.studysmart.homeScreen.util.InputFieldOptions
import com.habiba.studysmart.homeScreen.util.SubjectsColors

@Composable
fun AddNewSubjectDialogBox(
    homeScreenEvents: (HomeScreenEvents)->Unit,
    homeScreenState: HomeScreenState,
    subjectSelectedColor: SubjectsColors = SubjectsColors.PurpleGradient,
    isDialogOpened: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
){
    PopupDialog(
        onDismiss = { onDismiss() },
        onConfirm = { onConfirm() },
        isDialogOpened = isDialogOpened,
        confirmBtnText = R.string.add_subject_dialog_confirm_btn,
        dismissBtnText = R.string.add_subject_dialog_dismiss_btn,
        title = R.string.add_subject_dialog_title,
        titleIcon = R.drawable.img_books,
    )
    {
        Column (
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.add_subject_dialog_spacedby_vertical_element))
        ){
            // we create enum class of the colors to be generically updated and read here
            DialogBoxColorsTab(
                colorsList = SubjectsColors.entries,
                subjectSelectedColor,
                homeScreenEvents
            )
            NewSubjectInputField(
                homeScreenEvents = { homeScreenEvents(it) },
                homeScreenState= homeScreenState,
                inputFieldLabel = stringResource(R.string.add_subject_dialog_subject_name_input_field_label),
                inputFieldOption = InputFieldOptions.SubjectName
            )
            NewSubjectInputField(
                homeScreenEvents = { homeScreenEvents(it) },
                homeScreenState= homeScreenState,
                inputFieldLabel = stringResource(R.string.add_subject_dialog_hour_goal_input_field_label),
                inputFieldOption = InputFieldOptions.GoalHour
            )

        }




    }
}