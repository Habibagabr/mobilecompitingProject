package com.habiba.studysmart.sessionScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.habiba.studysmart.R
import com.habiba.studysmart.common.components.EmptySection
import com.habiba.studysmart.common.components.ScreenHeader
import com.habiba.studysmart.common.components.SectionHeader
import com.habiba.studysmart.common.components.recentlyStudyList
import com.habiba.studysmart.navigation.Home
import com.habiba.studysmart.sessionScreen.ui.components.SubjectDropDownList
import com.habiba.studysmart.sessionScreen.ui.components.TimerBtn
import com.habiba.studysmart.sessionScreen.ui.components.TimerSection
import com.habiba.studysmart.sessionScreen.utils.BtnTypes
import com.habiba.studysmart.sessionScreen.utils.SessionPhase
import com.habiba.studysmart.sessionScreen.viewModel.SessionScreenEvents
import com.habiba.studysmart.sessionScreen.viewModel.SessionScreenState

@Composable
fun SessionScreen(
    navController: NavController,
    state: SessionScreenState,
    onEvent: (SessionScreenEvents) -> Unit
) {
    Scaffold { innerPadding ->
        LazyColumn( modifier = Modifier .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding( horizontal = dimensionResource(R.dimen.app_horizontal_padding),
                vertical = dimensionResource(R.dimen.home_screen_vertical_padding) ),
            verticalArrangement = Arrangement.spacedBy( dimensionResource(R.dimen.home_screen_vertical_padding) )
        )
        {
            item {
                ScreenHeader(
                    onBackBtnClicked = { navController.navigate(Home){
                        popUpTo(Home){inclusive=true}
                    } },
                    screenHeader = R.string.session_screen_header )
                TimerSection(
                    elapsedSeconds = state.elapsedSeconds
                )
            }
            item {
                SubjectDropDownList(
                    subjects = state.subjects,
                    selectedSubject = state.selectedSubject,
                    { onEvent(SessionScreenEvents.SubjectSelected(it)) }
                )
            }
            item{
                Row(
                    modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                when (state.phase) {

                        SessionPhase.IDLE -> {
                            TimerBtn(BtnTypes.CANCEL , enabled = false) {

                            }
                            TimerBtn(BtnTypes.START) {
                                onEvent(SessionScreenEvents.StartBtnClicked)
                            }
                            TimerBtn(BtnTypes.FINISH , enabled = false) {

                            }

                        }

                        SessionPhase.RUNNING -> {
                            TimerBtn(BtnTypes.CANCEL , enabled = false) {

                            }
                            TimerBtn(BtnTypes.STOP) {
                                onEvent(SessionScreenEvents.StopBtnClicked)
                            }
                            TimerBtn(BtnTypes.FINISH , enabled = false) {

                            }

                        }

                        SessionPhase.PAUSED -> {
                            TimerBtn(BtnTypes.CANCEL , enabled = true) {
                                onEvent(SessionScreenEvents.CancelBtnClicked)

                            }

                            TimerBtn(BtnTypes.RESUME) {
                                onEvent(SessionScreenEvents.ResumeBtnClicked)
                            }
                            TimerBtn(BtnTypes.FINISH) {
                                onEvent(SessionScreenEvents.FinishBtnClicked)
                            }
                        }

                        SessionPhase.FINISHED -> {
                            TimerBtn(BtnTypes.CANCEL , enabled = false) {

                            }
                            TimerBtn(BtnTypes.START) {
                                onEvent(SessionScreenEvents.StartBtnClicked)
                            }
                            TimerBtn(BtnTypes.FINISH , enabled = false) {

                            }

                        }
                    }
                }
            }

            item {
                SectionHeader(sectionTitle = stringResource(R.string.recently_studied_header))
            }

            if (state.historySessions.isNotEmpty()) {
                    recentlyStudyList(
                        state.historySessions,
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
