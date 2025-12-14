package com.habiba.studysmart.subjectScreen.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.habiba.studysmart.R

@Composable
fun ProgressIndicator(
    goalHours: Number = 0.0,
    actualStudiedHours: Number = 0.0
) {
    val goal = goalHours.toFloat()
    val actual = actualStudiedHours.toFloat()

    val progress =
        if (goal == 0f) 0f
        else (actual / goal).coerceIn(0f, 1f)

    Box(
        modifier = Modifier.size(dimensionResource(R.dimen.circular_indicator_size)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { 1f },
            modifier = Modifier.fillMaxSize(),
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxSize(),
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = "${ (progress * 100).toInt() }%",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
