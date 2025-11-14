package com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.habiba.studysmart.homeScreen.domain.model.TaskModel
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenState

// lazy list scope means : not a new scope and not a new compose its an extension to the parent lazy list of it
fun LazyListScope.upComingTasksList(
    upComingTasksList: List<TaskModel>,
    homeScreenEvents: (HomeScreenEvents)->Unit,
    homeScreenState: HomeScreenState = HomeScreenState(),
    onTaskClicked: () -> Unit, // will be removed and be the same as onCheckBoxClicked
) {
    if(upComingTasksList.isNotEmpty()) {
        items(upComingTasksList) { item ->
            UpcomingTaskCard(
                task=item,
                onTaskClicked = onTaskClicked,
                onCheckBoxClicked = { homeScreenEvents(HomeScreenEvents.TaskCompleted(item.taskId)) }
            )
        }
    }

}
