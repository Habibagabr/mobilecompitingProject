package com.habiba.studysmart.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.habiba.studysmart.R
import com.habiba.studysmart.domain.model.TaskDomainModel
import com.habiba.studysmart.homeScreen.util.PriorityLevel

@Composable
fun UpcomingTaskCard(
    isHomeScreen: Boolean,
    task: TaskDomainModel,
    onCheckBoxClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            )
            .padding(dimensionResource(R.dimen.upcoming_tasks_card_padding)),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.upcoming_tasks_card_spacedBy)
        ),
        verticalAlignment = Alignment.Top
    ) {

        TaskCheckBox(
            priorityLevel = PriorityLevel.getPriorityLevel(task.priority),
            isCompleted = task.isCompleted,
            onCheckBoxClicked = onCheckBoxClicked
        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.upcoming_tasks_card_spacedBy)
                )
            ) {
                Text(
                    text = task.title,
                    textDecoration = if (task.isCompleted)
                        TextDecoration.LineThrough
                    else TextDecoration.None,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (isHomeScreen) {
                    Text(
                        text = task.taskToSubject,
                        textDecoration = if (task.isCompleted)
                            TextDecoration.LineThrough
                        else TextDecoration.None,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = task.description,
                    textDecoration = if (task.isCompleted)
                        TextDecoration.LineThrough
                    else TextDecoration.None,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = task.taskDate,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}
