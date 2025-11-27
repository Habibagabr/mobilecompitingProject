package com.habiba.studysmart.common.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.habiba.studysmart.homeScreen.domain.model.SessionModel
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents

fun LazyListScope.recentlyStudyList(recentlyStudiedSessions: List<SessionModel> , homeScreenEvents: (HomeScreenEvents)->Unit = {}) {
    items(recentlyStudiedSessions){item ->
        RecentlyStudySessionCard(
            subject = item,
            homeScreenEvents = homeScreenEvents

        )
    }


}