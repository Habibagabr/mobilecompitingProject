package com.habiba.studysmart.homeScreen.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.ui.components.deleteSessionDialogBoxComponents.DeleteSessionDialogBox
import com.habiba.studysmart.homeScreen.ui.components.newSubjectDialogBoxComponents.AddNewSubjectDialogBox
import com.habiba.studysmart.common.components.EmptySection
import com.habiba.studysmart.common.components.recentlyStudyList
import com.habiba.studysmart.common.components.SectionHeader
import com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents.TaskKpiList
import com.habiba.studysmart.common.components.upComingTasksList
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenState
import com.habiba.studysmart.homeScreen.ui.sections.HomeScreenTopBarSection
import com.habiba.studysmart.homeScreen.ui.sections.StartStudySessionSection
import com.habiba.studysmart.homeScreen.ui.sections.SubjectsSection
import com.habiba.studysmart.navigation.SessionScreen
import com.habiba.studysmart.navigation.SubjectScreen

@Composable
fun HomeScreen(
    homeScreenState: HomeScreenState ,
    homeScreenEvents: (HomeScreenEvents) -> Unit = {},
    navController: NavHostController,
) {

    // -------- Dialogs --------
    if (homeScreenState.subjectDialogShowUp) {
        AddNewSubjectDialogBox(
            homeScreenEvents = homeScreenEvents,
            homeScreenState = homeScreenState,
            subjectSelectedColor = homeScreenState.colorSelected,
            onDismiss = {
                homeScreenEvents(HomeScreenEvents.NewSubjectDialogDismissedOrCanceled())
            },
            onConfirm = {
                homeScreenEvents(
                    HomeScreenEvents.NewSubjectDialogConfirmed(
                        homeScreenState.subjectName,
                        homeScreenState.subjectGoalHours,
                        homeScreenState.colorSelected
                    )
                )
            },
            isDialogOpened = true
        )
    }

    if (homeScreenState.deleteSessionDialogShowUp) {
        DeleteSessionDialogBox(
            onDismiss = {
                homeScreenEvents(HomeScreenEvents.DeleteSessionDialogDismissed())
            },
            onConfirm = {
                homeScreenEvents(HomeScreenEvents.DeleteSessionDialogConfirmed())
            },
            isDialogOpened = true
        )
    }

    // -------- Loader --------
    if (!homeScreenState.screenLoaded) {
        Log.d("habibaLOGSS","THE SCREEN still loading")

        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return

    }

        // -------- Screen --------
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

                item { HomeScreenTopBarSection(
                    events = {
                        homeScreenEvents(HomeScreenEvents.LogoutConfirmed())
                    },
                    navController = navController

                ) }

                item {
                    TaskKpiList(

                        subjectCount = homeScreenState.userData?.user?.subjectCount ?: 0,
                        goalStudyHour = homeScreenState.userData?.user?.studyGoalHour ?: 0.0,
                        studiedHour = homeScreenState.userData?.user?.studiedHours ?: 0.0,
                    )
                }

                item {
                    SubjectsSection(
                        homeScreenState.subjectList,
                        onAddSubjectClicked = {
                            homeScreenEvents(HomeScreenEvents.AddNewSubjectBtnClicked())
                        },
                        onSubjectClicked = { subject ->
                            navController.navigate(SubjectScreen(subjectId = subject.id!!))
                        }
                    )
                }

                item { StartStudySessionSection(
                    onBtnClicked = {navController.navigate(SessionScreen(homeScreenState.userData?.user?.userId?:"0"))}
                ) }

                item {
                    SectionHeader(sectionTitle = stringResource(R.string.upcoming_tasks_header))
                }

                if (homeScreenState.upComingList.isNotEmpty()) {
                    upComingTasksList(
                        isHomeScreen = true,
                        upComingTasksList = homeScreenState.upComingList,
                        homeScreenEvents = homeScreenEvents,
                    )
                } else {
                    item {
                        EmptySection(
                            emptyImage = R.drawable.img_tasks,
                            emptyText = stringResource(R.string.add_task_text)
                        )
                    }
                }

                item {
                    SectionHeader(sectionTitle = stringResource(R.string.recently_studied_header))
                }

                if (homeScreenState.recentlyStudySessionsList.isNotEmpty()) {
                    recentlyStudyList(
                        homeScreenState.recentlyStudySessionsList,
                    )
                } else {
                    item {
                        EmptySection(
                            emptyImage = R.drawable.img_lamp,
                            emptyText = stringResource(R.string.add_session_text)
                        )
                    }
                }
            }
        }

}
