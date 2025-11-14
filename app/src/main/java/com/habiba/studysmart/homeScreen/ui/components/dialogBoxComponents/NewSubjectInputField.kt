package com.habiba.studysmart.homeScreen.ui.components.dialogBoxComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenState
import com.habiba.studysmart.homeScreen.util.InputFieldOptions


@Composable
fun NewSubjectInputField(
    homeScreenEvents: (HomeScreenEvents)->Unit,
    homeScreenState : HomeScreenState,
    inputFieldLabel:String = "",
    inputFieldOption: InputFieldOptions = InputFieldOptions.SubjectName
){
    Column {
        OutlinedTextField(
            value =
                when (inputFieldOption) {
                    InputFieldOptions.SubjectName -> homeScreenState.subjectName
                    InputFieldOptions.GoalHour -> homeScreenState.subjectGoalHours
                },
            onValueChange = { newValue ->
                when (inputFieldOption) {
                    InputFieldOptions.SubjectName -> homeScreenEvents(
                        HomeScreenEvents.SubjectNameFieldChanged(
                            newValue
                        )
                    )

                    InputFieldOptions.GoalHour -> homeScreenEvents(
                        HomeScreenEvents.GoalHourFieldChanged(
                            newValue
                        )
                    )
                }
            },
            label = { Text(text = inputFieldLabel) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = when (inputFieldOption) {
                    InputFieldOptions.SubjectName -> androidx.compose.ui.text.input.KeyboardType.Text
                    InputFieldOptions.GoalHour -> androidx.compose.ui.text.input.KeyboardType.Number
                }
            ),
            singleLine = true,
        )
        if (homeScreenState.isSubjectNameError || homeScreenState.isGoalHourError) {
            Text(
                text = when (inputFieldOption) {
                    InputFieldOptions.SubjectName -> homeScreenState.subjectError
                    InputFieldOptions.GoalHour -> homeScreenState.goalHourError
                },
                color = MaterialTheme.colorScheme.error,
            )
        }
    }





}