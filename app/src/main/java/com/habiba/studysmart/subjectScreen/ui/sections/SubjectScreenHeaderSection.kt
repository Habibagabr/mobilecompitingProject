package com.habiba.studysmart.subjectScreen.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.habiba.studysmart.R

@Composable
fun SubjectScreenHeaderSection(
    onBackBtnPressed:()->Unit
){
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier.clickable{
                    onBackBtnPressed()
                },
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )


        }

}