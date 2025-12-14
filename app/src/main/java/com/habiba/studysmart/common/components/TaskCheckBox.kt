package com.habiba.studysmart.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.util.PriorityLevel




@Composable
fun TaskCheckBox(
    priorityLevel: PriorityLevel,
    isCompleted:Boolean,
    onCheckBoxClicked:()-> Unit
){

    Box(
        modifier = Modifier
            .size(dimensionResource(R.dimen.tasks_priority_circle_size))
            .clip(CircleShape)
            .border(dimensionResource(R.dimen.tasks_priority_border_circle_size) ,PriorityLevel.getPriorityColor(priorityLevel.value),CircleShape)
            .clickable{onCheckBoxClicked()}
        ,
        contentAlignment = Alignment.Center
    ){
            AnimatedVisibility(isCompleted) {
                Icon(
                    painter = painterResource(R.drawable.baseline_check_24),
                    contentDescription = null,
                    tint = PriorityLevel.getPriorityColor(priorityLevel.value)

                )
            }

    }

}


//@Preview
//@Composable
//fun TaskPriorityCirclePreview(){
//    TaskCheckBox("high")
//}