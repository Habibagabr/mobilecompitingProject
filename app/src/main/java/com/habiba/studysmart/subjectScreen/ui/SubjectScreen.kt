package com.habiba.studysmart.subjectScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.habiba.studysmart.R
import com.habiba.studysmart.common.components.EmptySection
import com.habiba.studysmart.common.components.SectionHeader
import com.habiba.studysmart.common.components.recentlyStudyList
import com.habiba.studysmart.common.components.upComingTasksList
import com.habiba.studysmart.navigation.Home
import com.habiba.studysmart.navigation.TaskScreen
import com.habiba.studysmart.subjectScreen.ui.components.SearchOnGoogleFAB
import com.habiba.studysmart.subjectScreen.ui.components.SubjectScreenFAB
import com.habiba.studysmart.subjectScreen.ui.sections.SubjectKpiBarSection
import com.habiba.studysmart.subjectScreen.ui.sections.SubjectScreenHeaderSection
import com.habiba.studysmart.subjectScreen.ui.viewModel.SubjectScreenEvents
import com.habiba.studysmart.subjectScreen.ui.viewModel.SubjectScreenState


@Composable
fun SubjectScreen(
    state: SubjectScreenState = SubjectScreenState(),
    events : (SubjectScreenEvents)->Unit,
    navController: NavController
) {

    Scaffold(
        floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.fb_space))
            ) {

                SearchOnGoogleFAB(
                    subjectName = state.screenDetails?.subject?.name ?: ""
                )

                SubjectScreenFAB(
                    onClick = {
                        navController.navigate(
                            TaskScreen(
                                subjectId=state.screenDetails?.subject?.id ?: 1,
                                subjectName = state.screenDetails?.subject?.name?:""
                            )
                        )
                    }
                )
            }
        }

    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(
                    horizontal = dimensionResource(R.dimen.app_horizontal_padding),
                    vertical = dimensionResource(R.dimen.home_screen_vertical_padding)
                ),
            contentPadding = PaddingValues(
                bottom = 32.dp
            ),

            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.home_screen_vertical_padding)
            )
        ) {
            item {
                SubjectScreenHeaderSection(
                    onBackBtnPressed = { navController.navigate(Home){
                        popUpTo(Home){inclusive=false}
                    } }

                )
            }
            item {
                Text(
                    text = state.screenDetails?.subject?.name?:"",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            item {
                SubjectKpiBarSection(
                    goalStudyHours = state.screenDetails?.subject?.goalHours ?: 0.0,
                    studiedSeconds = state.studiedSeconds
                )
            }
            item {
                SectionHeader(sectionTitle = stringResource(R.string.upcoming_tasks_header))
            }

            if (state.upComingTasks.isNullOrEmpty()) {
                item {
                    EmptySection(
                        emptyImage = R.drawable.img_tasks,
                        emptyText = stringResource(R.string.add_task_text)
                    )
                }
            } else {
                upComingTasksList(
                    isHomeScreen = false,
                    upComingTasksList = state.upComingTasks,
                )

            }

            item {
                SectionHeader(
                    sectionTitle = stringResource(R.string.completed_task_header)
                )
            }

            if (state.completedTasks.isNullOrEmpty()) {
                item {
                    EmptySection(
                        emptyImage = R.drawable.img_tasks,
                        emptyText = stringResource(R.string.do_not_have_completed_task_text)
                    )
                }

            } else {
                upComingTasksList(
                    isHomeScreen = false,
                    upComingTasksList = state.completedTasks.filter { it.isCompleted },
                )

            }

            item {
                SectionHeader(
                    sectionTitle = stringResource(R.string.recently_studied_header)
                )
            }

            if (state.recentlyStudiedSession.isNullOrEmpty()) {
                item {
                    EmptySection(
                        emptyImage = R.drawable.img_lamp,
                        emptyText = stringResource(R.string.add_session_text)
                    )
                }

            } else {
                recentlyStudyList(
                    state.recentlyStudiedSession,
                )

            }
        }
    }
}
