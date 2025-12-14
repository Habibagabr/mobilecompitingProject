package com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.habiba.studysmart.R
import com.habiba.studysmart.ui.theme.greenGradient

@Composable
fun SubjectCard(
    subjectTitle:String,
    cardBackgroundColor: List<Color>?,
    onSubjectClicked:()->Unit
){
    Column(
        modifier = Modifier
            .width(dimensionResource(R.dimen.subject_card_width))
            .height(dimensionResource(R.dimen.subject_card_height))
            .background(brush = Brush.linearGradient(colors = cardBackgroundColor ?: greenGradient),shape=MaterialTheme.shapes.medium)
            .padding(dimensionResource(R.dimen.card_padding))
            .clickable{onSubjectClicked()},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Image(
            painter = painterResource(R.drawable.img_books),
            contentDescription = subjectTitle,
            modifier = Modifier.size(dimensionResource(R.dimen.subject_image_size)),

        )
        Text(
            text = subjectTitle,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

