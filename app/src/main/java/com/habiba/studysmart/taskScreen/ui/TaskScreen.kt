package com.habiba.studysmart.taskScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.habiba.studysmart.R
import com.habiba.studysmart.authentecationScreens.InputFieldType
import com.habiba.studysmart.common.components.CustomizedInputField
import com.habiba.studysmart.common.components.PopupDialog
import com.habiba.studysmart.common.components.SectionHeader
import com.habiba.studysmart.navigation.SubjectScreen
import com.habiba.studysmart.taskScreen.PriorityLevels
import com.habiba.studysmart.taskScreen.ui.components.DueDateSection
import com.habiba.studysmart.taskScreen.ui.components.PriorityTabs
import com.habiba.studysmart.common.components.ScreenHeader
import com.habiba.studysmart.taskScreen.ui.viewModel.TaskScreenEvents
import com.habiba.studysmart.taskScreen.ui.viewModel.TaskScreenState
import com.habiba.studysmart.ui.theme.Typography
import kotlinx.coroutines.delay

@Composable
fun TaskScreen(
    // will be removed
    subjectId : Int,
    state: TaskScreenState,
    onEvent:(TaskScreenEvents)->Unit,
    navController: NavController
) {
    LaunchedEffect(state.isTaskAddedSuccessfully) {
        if(state.isTaskAddedSuccessfully){
            onEvent(TaskScreenEvents.TaskAddedSuccessfully)
            delay(500)
            navController.navigate(SubjectScreen(subjectId)){
                popUpTo(SubjectScreen(subjectId)){
                    inclusive=false
                }
            }

        }
    }

    if(state.isDialogShowUp){
        PopupDialog(
            isDialogOpened = true,
            title =R.string.task_added,
            titleIcon = R.drawable.check
        )
    }
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(
                    horizontal = dimensionResource(R.dimen.app_horizontal_padding),
                    vertical = dimensionResource(R.dimen.home_screen_vertical_padding)
                ),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.home_screen_vertical_padding)
            )

        ) {
            item {
                ScreenHeader(
                    onBackBtnClicked = {
                        navController.navigate(SubjectScreen(subjectId)) {
                            popUpTo(SubjectScreen(subjectId)) {
                                inclusive = false
                            }
                        }
                    },
                    screenHeader = R.string.task_screen_header
                )
            }
            item {
                CustomizedInputField(
                    inputType = InputFieldType.TaskTitle,
                    input = state.taskTitle,
                    placeholder = stringResource(R.string.task_title_plachodler),
                    label = stringResource(R.string.task_title_lable),
                    onValueChange = { newText ->
                        onEvent(TaskScreenEvents.TaskTitleChanged(newText))
                    },
                    errorMsg = state.taskTitleError,
                    error =  !state.isTitleValid
                )

            }
            item {
                CustomizedInputField(
                    inputType = InputFieldType.TaskDescription,
                    input = state.taskDescription,
                    placeholder = stringResource(R.string.task_descripion_plachodler),
                    label = stringResource(R.string.task_description_lable),
                    onValueChange = { newText ->
                        onEvent(TaskScreenEvents.TaskDescriptionChanged(newText))
                    },
                    errorMsg = state.taskDescriptionError ,
                    error = !state.isDescriptionValid
                )

            }
            item {
                DueDateSection(
                    state = state,
                    events = onEvent
                )

            }
            item {
                SectionHeader(
                    sectionTitle = stringResource(R.string.priority_header)
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PriorityTabs(
                        state = state,
                        priorityLevel = PriorityLevels.LOW,
                        onPriorityPressed = {
                            onEvent(
                                TaskScreenEvents.PriorityPressed(PriorityLevels.LOW)
                            )
                        }
                    )
                    PriorityTabs(
                        state = state,
                        priorityLevel = PriorityLevels.MEDIUM,
                        onPriorityPressed = {
                            onEvent(
                                TaskScreenEvents.PriorityPressed(PriorityLevels.MEDIUM)
                            )
                        }

                    )
                    PriorityTabs(
                        state = state,
                        priorityLevel = PriorityLevels.HIGH,
                        onPriorityPressed = {
                            onEvent(
                                TaskScreenEvents.PriorityPressed(PriorityLevels.HIGH)
                            )
                        }

                    )

                }


            }

            item {
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            onEvent(TaskScreenEvents.SaveBtnClicked)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = stringResource(R.string.save_btn),
                            style = Typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}
