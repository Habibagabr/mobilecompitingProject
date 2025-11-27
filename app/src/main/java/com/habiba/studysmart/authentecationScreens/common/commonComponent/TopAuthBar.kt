package com.habiba.studysmart.authentecationScreens.common.commonComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.habiba.studysmart.R

@Composable
fun TopAuthBar(
    screenTitle:String,
    screenSubTitle:String
){

    Box(
        modifier = Modifier
            .padding(bottom = dimensionResource(R.dimen.top_auth_vertical_bar_padding))
            .fillMaxWidth()
            .background(color = Color.DarkGray,
                shape = RoundedCornerShape(
                    bottomEnd = dimensionResource(R.dimen.top_bar_bottom_end_corner))
            )
            .padding(vertical = dimensionResource(R.dimen.top_auth_vertical_bar_padding), horizontal = dimensionResource(R.dimen.top_auth_horizontal_bar_padding))
        ,
    ){
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = screenTitle,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = screenSubTitle,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

        }

    }
}