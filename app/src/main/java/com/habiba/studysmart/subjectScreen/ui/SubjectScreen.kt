package com.habiba.studysmart.subjectScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.habiba.studysmart.R
import com.habiba.studysmart.common.components.EmptySection
import com.habiba.studysmart.common.components.SectionHeader
import com.habiba.studysmart.common.components.recentlyStudyList
import com.habiba.studysmart.common.components.upComingTasksList
import com.habiba.studysmart.homeScreen.domain.model.SessionModel
import com.habiba.studysmart.homeScreen.domain.model.TaskModel
import com.habiba.studysmart.subjectScreen.ui.components.SubjectScreenFAB
import com.habiba.studysmart.subjectScreen.ui.sections.SubjectKpiBarSection
import com.habiba.studysmart.subjectScreen.ui.sections.SubjectScreenHeaderSection

val upComingList = listOf<TaskModel>(
TaskModel(
taskTitle = "meeting the career tasks on the playgaurd",
taskPriority = 2,
taskDue = "22 oct 2025",
isCompleted = true,
taskId=6

),
TaskModel(
taskTitle = "meeting the career tasks on the playgaurd",
taskPriority = 0,
taskDue = "22 oct 2025",
isCompleted = false,
taskId=7

),
TaskModel(
taskTitle = "meeting the career tasks on the playgaurd",
taskPriority = 2,
taskDue = "22 oct 2025",
isCompleted = true,
taskId=1

),
TaskModel(
taskTitle = "meeting the career tasks on the playgaurd",
taskPriority = 1,
taskDue = "22 oct 2025",
isCompleted = true,
taskId=2


),
TaskModel(
taskTitle = "meeting the career tasks on the playgaurd",
taskPriority = 0,
taskDue = "22 oct 2025",
isCompleted = false,
taskId=3


),
TaskModel(
taskTitle = "meeting the career tasks on the playgaurd",
taskPriority = 2,
taskDue = "22 oct 2025",
isCompleted = true,
taskId=4



),
TaskModel(
taskTitle = "meeting the career tasks on the playgaurd",
taskPriority = 2,
taskDue = "22 oct 2025",
isCompleted = true,
taskId=5



),
TaskModel(
taskTitle = "meeting the career tasks on the playgaurd",
taskPriority = 1,
taskDue = "22 oct 2025",
isCompleted = true,
taskId=0



),
TaskModel(
taskTitle = "meeting the career tasks on the playgaurd",
taskPriority = 2,
taskDue = "22 oct 2025",
isCompleted = false,
taskId=8

)
)
val recentlyStudySessionsList= listOf(
    SessionModel(
        relatedToSubject = "English",
        date = "22 oct 2022",
        duration = 2,
        sessionSubjectId = 1,
        sessionId = 0,

        ),
SessionModel(
relatedToSubject = "arabic",
date= "22 oct 2022",
duration= 5,
sessionSubjectId=2,
sessionId= 1 ,
),
SessionModel(
relatedToSubject = "physics",
date= "22 oct 2022",
duration= 10,
sessionSubjectId=3,
sessionId= 2 ,
),
SessionModel(
relatedToSubject = "chemistry",
date= "22 oct 2022",
duration= 7,
sessionSubjectId=3,
sessionId= 3 ,
),
SessionModel(
relatedToSubject = "maths",
date= "22 oct 2022",
duration= 6,
sessionSubjectId=4,
sessionId= 4 ,
),
SessionModel(
relatedToSubject = "social study",
date= "22 oct 2022",
duration= 2,
sessionSubjectId=5,
sessionId= 5 ,
),
)


@Composable
fun SubjectScreen() {

    Scaffold(
        floatingActionButton = {
            SubjectScreenFAB(
                onClick = {
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = dimensionResource(R.dimen.app_horizontal_padding)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.home_screen_vertical_padding)
            )
        ) {
            item {
                SubjectScreenHeaderSection()
            }
            item {
                Text(
                    text = "English",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            item {
                SubjectKpiBarSection()
            }
            item {
                SectionHeader(sectionTitle = stringResource(R.string.upcoming_tasks_header))
            }

            if (upComingList.isNotEmpty()) {
                upComingTasksList(
                    upComingTasksList = upComingList,
                    onTaskClicked = { }
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
                SectionHeader(
                    sectionTitle = stringResource(R.string.completed_task_header)
                )
            }

            if (upComingList.isNotEmpty()) {
                upComingTasksList(
                    upComingTasksList = upComingList.filter { it.isCompleted },
                    onTaskClicked = { }
                )
            } else {
                item {
                    EmptySection(
                        emptyImage = R.drawable.img_tasks,
                        emptyText = stringResource(R.string.do_not_have_completed_task_text)
                    )
                }
            }

            item {
                SectionHeader(
                    sectionTitle = stringResource(R.string.recently_studied_header)
                )
            }

            if (recentlyStudySessionsList.isNotEmpty()) {
                recentlyStudyList(
                    recentlyStudySessionsList,
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
