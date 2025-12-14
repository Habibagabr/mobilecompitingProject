package com.habiba.studysmart.subjectScreen.ui.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.habiba.studysmart.subjectScreen.domain.usecasesInterface.IGetSubjectDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectScreenViewModel@Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getSubjectDetailsUseCase: IGetSubjectDetailsUseCase
): ViewModel() {
    private val _state : MutableStateFlow<SubjectScreenState> =MutableStateFlow( SubjectScreenState())
    val state : StateFlow<SubjectScreenState> = _state

    init{
        onEvent(SubjectScreenEvents.ScreenLoaded)
    }
    fun onEvent(event: SubjectScreenEvents){
        when(event){
            SubjectScreenEvents.ScreenLoaded ->onScreenLoaded()
        }
    }

    private fun onScreenLoaded() {
        val subjectId: Int? = savedStateHandle.get<Int>("subjectId")

        subjectId?.let { id ->
            viewModelScope.launch {

                val subjectDetails = getSubjectDetailsUseCase(id)

                val tasks = subjectDetails.tasks ?: emptyList()
                val completedTasks = tasks.filter { it.isCompleted }
                val upComingTasks = tasks - completedTasks

                val sessions = subjectDetails.sessions ?: emptyList()

                val studiedSeconds = sessions.sumOf { it.duration ?: 0L }

                _state.value = _state.value.copy(
                    screenDetails = subjectDetails,
                    completedTasks = completedTasks,
                    upComingTasks = upComingTasks,
                    recentlyStudiedSession = sessions,
                    studiedSeconds = studiedSeconds,
                    screenLoaded = true
                )
            }
        }
    }

}