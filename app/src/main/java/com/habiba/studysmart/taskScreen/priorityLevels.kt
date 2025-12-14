package com.habiba.studysmart.taskScreen

import androidx.compose.ui.graphics.Color

enum class PriorityLevels(val level: String, val color: Color) {
    LOW(level = "Low", color = Color(0xFF1C8C43)),
    MEDIUM(level = "Medium", color = Color(0xFFF28705)),
    HIGH(level = "High", color = Color(0xFFD93030))
}
