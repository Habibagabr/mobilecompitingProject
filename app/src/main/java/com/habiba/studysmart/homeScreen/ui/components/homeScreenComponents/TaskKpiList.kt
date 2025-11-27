package com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.habiba.studysmart.R
import com.habiba.studysmart.common.components.TasksKpiCard

@Composable
fun TaskKpiList(){
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        Box(
            modifier=Modifier.weight(1f)
        ) {
            TasksKpiCard(stringResource(R.string.subject_kpi_card), 4)
        }
        Box(
            modifier=Modifier.weight(1f)
        ) {

            TasksKpiCard(stringResource(R.string.studied_hour_kpi_card), 8.8)
        }
        Box(
            modifier=Modifier.weight(1f)
        ) {

            TasksKpiCard(stringResource(R.string.goal_kpi_card), 17.0)
        }


    }

}