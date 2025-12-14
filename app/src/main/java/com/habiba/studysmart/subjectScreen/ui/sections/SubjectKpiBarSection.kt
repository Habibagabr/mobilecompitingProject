package com.habiba.studysmart.subjectScreen.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import com.habiba.studysmart.common.components.TasksKpiCard
import com.habiba.studysmart.common.utils.secondsToHours
import com.habiba.studysmart.subjectScreen.ui.components.ProgressIndicator

@Composable
fun SubjectKpiBarSection(
    goalStudyHours: Number = 0.0,
    studiedSeconds: Long = 0L,
) {
    val studiedHours = secondsToHours(studiedSeconds)

    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        item {
            TasksKpiCard(
                kpiTitle = "Goal Study Hours",
                kpiValue = goalStudyHours
            )
        }
        item {
            TasksKpiCard(
                kpiTitle = "Study Hours",
                kpiValue = studiedHours
            )
        }
        item {
            ProgressIndicator(
                goalHours = goalStudyHours,
                actualStudiedHours = studiedHours
            )
        }
    }
}
