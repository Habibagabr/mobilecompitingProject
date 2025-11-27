package com.habiba.studysmart.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.domain.model.SessionModel
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents

@Composable
fun RecentlyStudySessionCard(
    subject: SessionModel,
    homeScreenEvents: (HomeScreenEvents)->Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            )
            .padding(all = dimensionResource(R.dimen.upcoming_tasks_card_padding)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.upcoming_tasks_card_spacedBy))
        ){
            Text(
                text = subject.relatedToSubject,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = subject.date,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.recently_study_spacedBy))
        ){
            Text(
                text = subject.duration.toString() + " hr",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Icon(
                modifier = Modifier.clickable {
                    homeScreenEvents(HomeScreenEvents.DeleteSessionClicked(subject.sessionId))},
                painter = painterResource(R.drawable.img_books),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }



    }
}