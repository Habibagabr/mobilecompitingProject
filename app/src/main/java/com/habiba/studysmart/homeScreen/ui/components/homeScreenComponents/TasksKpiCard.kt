package com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.habiba.studysmart.R

@Composable
fun TasksKpiCard(
    kpiTitle:String="Subject Count",
    kpiValue: Number
){
    val kpiValueString = kpiValueDataType(kpiValue)
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.card_padding))
            .height(dimensionResource(R.dimen.kpi_card_height))
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            )
            .padding(dimensionResource(R.dimen.card_padding)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = kpiTitle,
            textAlign = TextAlign.Center,
            maxLines = 2,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            textAlign = TextAlign.Center,
            text = kpiValueString,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )


    }


}

private fun kpiValueDataType(kpiValue: Number):String{
    return if(kpiValue.toDouble() % 1 ==0.0 ) kpiValue.toInt().toString()
    else kpiValue.toString()

}

//@Preview
//@Composable
//fun TasksKpiCardPreview(){
//    TasksKpiCard(
//        "Subject Count",
//        66
//    )
//}