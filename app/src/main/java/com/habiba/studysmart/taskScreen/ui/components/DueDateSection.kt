package com.habiba.studysmart.taskScreen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.habiba.studysmart.R
import com.habiba.studysmart.taskScreen.ui.viewModel.TaskScreenEvents
import com.habiba.studysmart.taskScreen.ui.viewModel.TaskScreenState
import com.habiba.studysmart.ui.theme.Typography
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DueDateSection(
    state: TaskScreenState,
    events: (TaskScreenEvents) -> Unit
) {
    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = state.dueDateSelected.ifEmpty { stringResource(R.string.due_data_header) },
                style = Typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Image(
                painter = painterResource(R.drawable.schedule),
                contentDescription = null,
                modifier = Modifier
                    .size(28.dp)
                    .clickable { events(TaskScreenEvents.DatePickedPressed) }
            )
        }
        if(!state.isDueDateValid){
            Text(
                text = state.dueDateError,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }


    }

    if (state.isDatePickerDialogOpened) {
        DatePickerModal(
            onDateSelected = { selectedMillis ->
                selectedMillis?.let {
                    val sdf = SimpleDateFormat("dd - MM - yyyy", Locale.getDefault())
                    val selectedDate = sdf.format(Date(it))
                    events(TaskScreenEvents.DueDateSelected(selectedDate))
                }
            },
            onDismiss = { events(TaskScreenEvents.CloseDatePicker) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            androidx.compose.material3.TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            androidx.compose.material3.TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        text = {
            DatePicker(state = datePickerState)
        }
    )
}
