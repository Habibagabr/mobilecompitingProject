package com.habiba.studysmart.homeScreen.ui.components.deleteSessionDialogBoxComponents

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.habiba.studysmart.R
import com.habiba.studysmart.common.components.PopupDialog

@Composable
fun DeleteSessionDialogBox(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    isDialogOpened: Boolean
){
    PopupDialog(
        onDismiss = { onDismiss() },
        onConfirm = { onConfirm() },
        isDialogOpened = isDialogOpened,
        confirmBtnText= R.string.delete,
        dismissBtnText = R.string.cancel,
        title = R.string.delete_session_dialog_title,
        titleIcon = R.drawable.delete
    ) {
        Text(
            text = stringResource(R.string.delete_session_dialog_content),
            style= MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }


}