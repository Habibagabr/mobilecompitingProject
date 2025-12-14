package com.habiba.studysmart.common.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.habiba.studysmart.domain.model.SessionDomainModel
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents

fun LazyListScope.recentlyStudyList(
    recentlyStudiedSessions: List<SessionDomainModel>,
) {
    items(recentlyStudiedSessions){item ->
        RecentlyStudySessionCard(
            session = item,

        )
    }


}