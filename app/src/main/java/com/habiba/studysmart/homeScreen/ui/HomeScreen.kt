package com.habiba.studysmart.homeScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.domain.model.SessionModel
import com.habiba.studysmart.homeScreen.domain.model.SubjectModel
import com.habiba.studysmart.homeScreen.domain.model.TaskModel
import com.habiba.studysmart.homeScreen.ui.components.dialogBoxComponents.AddNewSubjectDialog
import com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents.EmptySection
import com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents.RecentlyStudyList
import com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents.SectionHeader
import com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents.TaskKpiList
import com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents.upComingTasksList
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenState
import com.habiba.studysmart.homeScreen.ui.sections.HomeScreenTopBarSection
import com.habiba.studysmart.homeScreen.ui.sections.StartStudySessionSection
import com.habiba.studysmart.homeScreen.ui.sections.SubjectsSection
import com.habiba.studysmart.ui.theme.blueGradient
import com.habiba.studysmart.ui.theme.darkBlueGradient
import com.habiba.studysmart.ui.theme.greenGradient
import com.habiba.studysmart.ui.theme.pinkOrangeGradient
import com.habiba.studysmart.ui.theme.purpleGradient


val recentlyStudiedSessions = listOf(
    SessionModel(
        relatedToSubject = "English",
         date= "22 oct 2022",
        duration= 2,
        sessionSubjectId=1,
        sessionId= 0 ,

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
val subjectsList=listOf(
    SubjectModel(
        name = "English",
        goalHours = 10f,
        subjectId = 1,
        subjectColor = greenGradient
    ),
    SubjectModel(
        name = "arabic",
        goalHours = 10f,
        subjectId = 2,
        subjectColor = pinkOrangeGradient


    ),
    SubjectModel(
        name = "physics",
        goalHours = 10f,
        subjectId = 3,
        subjectColor = darkBlueGradient
    ),
    SubjectModel(
        name = "chemistry",
        goalHours = 10f,
        subjectId = 4,
        subjectColor = purpleGradient
    ),
    SubjectModel(
        name = "maths",
        goalHours = 10f,
        subjectId = 5,
        subjectColor = blueGradient
    ),
    SubjectModel(
        name = "social study",
        goalHours = 10f,
        subjectId = 6,
        subjectColor = pinkOrangeGradient
    )
)


@Composable
fun HomeScreen(
    homeScreenState: HomeScreenState = HomeScreenState() ,
    homeScreenEvents: (HomeScreenEvents) -> Unit = {}
){
    /*

    * mutable state of + remember combo :
    * our Ui now depends on variable " when the isDialogOpened true we will make something "
    * so we need this variable to be a special type which is " State " means :
    * when this variable value changed please re-compose all of this Composable function again
    * okay , now we re-compose the function again so we need the mutable State of(false) not returning false again
    * so we used " remember " , okay why " remember Savable ??"
    * to keep the value even if we not just changing the state ,also when we change the configurations " like screen rotation"
    *
    * */


    AddNewSubjectDialog(
        homeScreenEvents = homeScreenEvents,
        homeScreenState = homeScreenState,
        subjectSelectedColor= homeScreenState.colorSelected,
        onDismiss = {
           homeScreenEvents(HomeScreenEvents.NewSubjectDialogDismissedOrCanceled())
        },
        onConfirm = {
            homeScreenEvents(HomeScreenEvents.NewSubjectDialogConfirmed(
                homeScreenState.subjectName,
                homeScreenState.subjectGoalHours
            ))
        },
        isDialogOpened = homeScreenState.subjectDialogShowUp
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = dimensionResource(R.dimen.app_horizontal_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.home_screen_vertical_padding))
    ) {

        item {
            HomeScreenTopBarSection()
        }
        item{
            TaskKpiList()
        }
        item {
            SubjectsSection(
                subjectsList,
                onAddSubjectClicked = {
                    homeScreenEvents(HomeScreenEvents.AddNewSubjectBtnClicked())
                }
            )
        }
        item {
            StartStudySessionSection()
        }
        item{
            SectionHeader(sectionTitle = stringResource(R.string.upcoming_tasks_header))

        }
        if(homeScreenState.upComingList.isNotEmpty()) {
            upComingTasksList(
                upComingTasksList = homeScreenState.upComingList,
                homeScreenState = homeScreenState,
                homeScreenEvents = homeScreenEvents,
                onTaskClicked = { } ,// will be removed,
            )
        }
        else{
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
        if (recentlyStudiedSessions.isNotEmpty()){
            RecentlyStudyList(recentlyStudiedSessions)
        }
        else{
            item{
                EmptySection(
                    emptyImage = R.drawable.img_lamp,
                    emptyText = stringResource(R.string.add_session_text)
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun HomeScreenPreview(){
//    HomeScreen()
//}