package com.habiba.studysmart.common.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.habiba.studysmart.domain.model.TaskDomainModel
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents

// lazy list scope means : not a new scope and not a new compose its an extension to the parent lazy list of it
fun LazyListScope.upComingTasksList(
    isHomeScreen: Boolean,
    upComingTasksList: List<TaskDomainModel>,
    homeScreenEvents: (HomeScreenEvents)->Unit={},
) {
    if(upComingTasksList.isNotEmpty()) {
        items(upComingTasksList) { item ->
            UpcomingTaskCard(
                isHomeScreen =isHomeScreen ,
                task = item,
                onCheckBoxClicked = { homeScreenEvents(HomeScreenEvents.TaskCompleted(item.taskId?:0)) }
            )
        }
    }

}
