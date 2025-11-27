package com.habiba.studysmart.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.domain.model.TaskModel
import com.habiba.studysmart.homeScreen.util.PriorityLevel

@Composable
fun UpcomingTaskCard(
    task: TaskModel,
    onTaskClicked: () -> Unit,
    onCheckBoxClicked: () -> Unit,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant , shape = MaterialTheme.shapes.medium)
            .padding(all= dimensionResource(R.dimen.upcoming_tasks_card_padding))
            .clickable{onTaskClicked},
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.upcoming_tasks_card_spacedBy)),
        verticalAlignment = Alignment.CenterVertically
    ){
        TaskCheckBox(
            priorityLevel = PriorityLevel.getPriorityLevel(task.taskPriority),
            isCompleted = task.isCompleted,
            onCheckBoxClicked = { onCheckBoxClicked() }
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.upcoming_tasks_card_spacedBy))
        ){
            Text(
                text = task.taskTitle,
                textDecoration= if(task.isCompleted)TextDecoration.LineThrough else TextDecoration.None,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = task.taskDue,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }


    }

}