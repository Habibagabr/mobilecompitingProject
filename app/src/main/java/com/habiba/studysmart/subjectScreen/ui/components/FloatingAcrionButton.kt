package com.habiba.studysmart.subjectScreen.ui.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.habiba.studysmart.R

@Composable
fun SubjectScreenFAB(onClick: () -> Unit) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        onClick = { onClick() },
    ) {
        Icon(painter = painterResource(R.drawable.img_lamp), null)
    }
}
