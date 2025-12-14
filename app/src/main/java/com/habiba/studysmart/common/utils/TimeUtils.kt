package com.habiba.studysmart.common.utils

import java.util.Locale

fun secondsToHours(seconds: Long): Float {
    return seconds / 3600f
}

fun secondsToFormattedHours(seconds: Long): String {
    val hours = seconds / 3600f
    return String.format(Locale.US, "%.1f h", hours)
}

fun secondsToHoursMinutes(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    return "%02d:%02d".format(hours, minutes)
}
