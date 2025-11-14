package com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.habiba.studysmart.homeScreen.domain.model.SessionModel

fun LazyListScope.RecentlyStudyList(recentlyStudiedSessions: List<SessionModel>) {
    items(recentlyStudiedSessions){item ->
        RecentlyStudySessionCard(item)
    }


}