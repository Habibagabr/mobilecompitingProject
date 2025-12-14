package com.habiba.studysmart.sessionScreen.ui.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface.IGetUserHistorySessionsUsecase
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface.IGetUserUniqueSubjectsUsecase
import com.habiba.studysmart.sessionScreen.domain.usecases.usecasesInterface.ISaveNewSessionUsecase
import com.habiba.studysmart.sessionScreen.utils.SessionPhase
import com.habiba.studysmart.sessionScreen.viewModel.SessionScreenEvents
import com.habiba.studysmart.sessionScreen.viewModel.SessionScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserHistorySessions: IGetUserHistorySessionsUsecase,
    private val getUserUniqueSubjects: IGetUserUniqueSubjectsUsecase,
     private val addSessionUsecase: ISaveNewSessionUsecase
) : ViewModel() {

    private val userId: String =
        savedStateHandle["userId"] ?: error("userId missing")

    private val _state = MutableStateFlow(SessionScreenState())
    val state: StateFlow<SessionScreenState> = _state

    private var timerJob: Job? = null

    init{
        onEvent(SessionScreenEvents.ScreenStarted)
    }

    fun onEvent(event: SessionScreenEvents) {
        when (event) {

            SessionScreenEvents.ScreenStarted -> loadInitialData()

            SessionScreenEvents.StartBtnClicked -> {
                startTimer()
                updatePhase(SessionPhase.RUNNING)
            }

            SessionScreenEvents.StopBtnClicked -> {
                stopTimer()
                updatePhase(SessionPhase.PAUSED)
            }

            SessionScreenEvents.ResumeBtnClicked -> {
                startTimer()
                updatePhase(SessionPhase.RUNNING)
            }

            SessionScreenEvents.CancelBtnClicked -> {
                resetTimer()
                updatePhase(SessionPhase.FINISHED)
            }

            SessionScreenEvents.FinishBtnClicked -> finishSession()

            is SessionScreenEvents.SubjectSelected -> {
                _state.value = _state.value.copy(
                    selectedSubject = event.subject
                )
            }
        }
    }

    // ---------------- Logic ----------------

    private fun loadInitialData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val subjects = getUserUniqueSubjects(userId)
            val sessions = getUserHistorySessions(userId)

            _state.value = _state.value.copy(
                subjects = subjects,
                historySessions = sessions,
                isLoading = false,
                phase = SessionPhase.IDLE
            )
            Log.d("Session vm","$subjects")
        }

    }

    private fun finishSession() {
        viewModelScope.launch {
            stopTimer()

            val subject = _state.value.selectedSubject ?: return@launch

            val newSession = SessionDomainModel(
                relatedToSubject = subject.subjectName,
                duration = _state.value.elapsedSeconds,
                date = System.currentTimeMillis().toString(),
                subjectId =subject.subjectId
            )

             addSessionUsecase(newSession)

            _state.value = _state.value.copy(
                historySessions = _state.value.historySessions + newSession,
                elapsedSeconds = 0,
                phase = SessionPhase.FINISHED
            )
        }
    }

    // ---------------- Timer ----------------

    private fun startTimer() {
        if (timerJob != null) return

        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _state.value =
                    _state.value.copy(
                        elapsedSeconds = _state.value.elapsedSeconds + 1
                    )
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    private fun resetTimer() {
        stopTimer()
        _state.value = _state.value.copy(elapsedSeconds = 0)
    }

    private fun updatePhase(phase: SessionPhase) {
        _state.value = _state.value.copy(phase = phase)
    }
}
