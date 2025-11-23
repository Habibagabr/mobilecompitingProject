package com.habiba.studysmart.subjectScreen.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import com.habiba.studysmart.commonUi.components.TasksKpiCard
import com.habiba.studysmart.subjectScreen.ui.components.ProgressIndicator

@Composable
fun SubjectKpiBarSection() {
    LazyRow (
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        item {
            TasksKpiCard(
                kpiTitle = "Goal Study Hours",
                kpiValue = 20
            )
        }
        item {
            TasksKpiCard(
                kpiTitle = "Study Hours",
                kpiValue = 20
            )
        }
            item{
                ProgressIndicator()
        }
    }

}