package com.habiba.studysmart.homeScreen.ui.components.dialogBoxComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.util.SubjectsColors

@Composable
fun DialogBoxColorItem(itemColor: SubjectsColors, onColorSelected: (SubjectsColors) -> Unit, selectedColor: SubjectsColors = SubjectsColors.PurpleGradient){

    Box(
        modifier = Modifier
            .size(dimensionResource(R.dimen.dialog_box_color_item_size))
            .clip(CircleShape)
            .border(width = dimensionResource(R.dimen.subject_color_border) , color = if(itemColor==selectedColor)MaterialTheme.colorScheme.onSurfaceVariant else Color.Transparent, shape = CircleShape)
            .background(brush = Brush.linearGradient(
                itemColor.color
            ))
            .clickable{
                onColorSelected(itemColor)
            },
        contentAlignment = Alignment.Center
    ){
        if(itemColor == selectedColor) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = Color.White
            )
        }


    }







}