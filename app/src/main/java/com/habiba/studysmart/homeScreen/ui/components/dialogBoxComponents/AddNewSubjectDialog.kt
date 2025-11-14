package com.habiba.studysmart.homeScreen.ui.components.dialogBoxComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenState
import com.habiba.studysmart.homeScreen.util.InputFieldOptions
import com.habiba.studysmart.homeScreen.util.SubjectsColors

@Composable
fun AddNewSubjectDialog(
    homeScreenEvents: (HomeScreenEvents)->Unit,
    homeScreenState: HomeScreenState,
    subjectSelectedColor: SubjectsColors = SubjectsColors.PurpleGradient,
    isDialogOpened: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
){
    if(isDialogOpened) {
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.surface,
            onDismissRequest = { onDismiss()},
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.add_subject_dialoge_spacedby)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_books),
                        modifier = Modifier.size(dimensionResource(R.dimen.add_subject_dialoge_icon_size)),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(R.string.add_subject_dialog_title),
                        style = MaterialTheme.typography.headlineMedium.copy(fontSize=dimensionResource(R.dimen.dialog_box_headline_size).value.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            text = {
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
                },
            dismissButton = {
                Text(
                    text = stringResource(R.string.add_subject_dialog_dismiss_btn),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(end = dimensionResource(R.dimen.add_subject_dialoge_spacedby))
                        .clickable (
                        onClick = { onDismiss() },
                        enabled = true
                        )
                )

            },
            confirmButton = {
                Text(
                    text = stringResource(R.string.add_subject_dialog_confirm_btn),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.
                    clickable (
                        onClick = { onConfirm() },
                        enabled = true,
                    )
                )
            },
        )
    }

}