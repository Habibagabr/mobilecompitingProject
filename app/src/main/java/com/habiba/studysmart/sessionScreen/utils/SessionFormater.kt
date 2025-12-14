package com.habiba.studysmart.sessionScreen.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

// -------- Duration Formatter --------
fun formatDuration(seconds: Long?): String {
    if (seconds == null || seconds == 0L) return "0 min"

    val hours = TimeUnit.SECONDS.toHours(seconds)
    val minutes = TimeUnit.SECONDS.toMinutes(seconds) % 60

    return when {
        hours > 0 -> "${hours}h ${minutes}m"
        else -> "${minutes} min"
    }
}

// -------- Date Formatter --------
fun formatDate(timestamp: String?): String {
    if (timestamp.isNullOrBlank()) return ""

    val millis = timestamp.toLongOrNull() ?: return ""

    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}
