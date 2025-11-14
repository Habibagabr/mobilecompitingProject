package com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.domain.model.SubjectModel
import com.habiba.studysmart.ui.theme.greenGradient


@Composable
fun SubjectsList(
    subjects:List<SubjectModel>
){
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.card_spacedby))
        )
        {
            items(subjects.size){
                SubjectCard(
                    subjectTitle = subjects[it].name,
                    cardBackgroundColor = subjects[it].subjectColor ?: greenGradient
                )
            }




    }
}