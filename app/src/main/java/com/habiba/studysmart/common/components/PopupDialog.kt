package com.habiba.studysmart.common.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.habiba.studysmart.R

@Composable
fun PopupDialog(
    onDismiss: () -> Unit= {},
    onConfirm: () -> Unit={},
    isDialogOpened: Boolean,
    confirmBtnText:Int?=null,
    dismissBtnText:Int?=null,
    title: Int,
    titleIcon: Int?=null,
    content: @Composable ()->Unit ={}
    ) {
    if(isDialogOpened) {
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.surface,
            onDismissRequest = { onDismiss()},
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.add_subject_dialoge_spacedby)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(titleIcon!=null) {
                        Image(
                            painter = painterResource(titleIcon),
                            modifier = Modifier.size(dimensionResource(R.dimen.add_subject_dialoge_icon_size)),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = stringResource(title),
                        style = MaterialTheme.typography.headlineMedium.copy(fontSize=dimensionResource(R.dimen.dialog_box_headline_size).value.sp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            text = {
              content()
            },
            dismissButton = {
                if (dismissBtnText!=null) {
                    Text(
                        text = stringResource(dismissBtnText),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .padding(end = dimensionResource(R.dimen.add_subject_dialoge_spacedby))
                            .clickable(
                                onClick = { onDismiss() },
                                enabled = true
                            )
                    )
                }

            },
            confirmButton = {
                if (confirmBtnText!=null) {
                    Text(
                        text = stringResource(confirmBtnText),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable(
                            onClick = { onConfirm() },
                        )
                    )
                }
            },
        )
    }

}
