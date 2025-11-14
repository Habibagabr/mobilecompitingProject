package com.habiba.studysmart.homeScreen.ui.components.dialogBoxComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents
import com.habiba.studysmart.homeScreen.util.SubjectsColors

@Composable
fun DialogBoxColorsTab(
    colorsList:List<SubjectsColors>,
    selectedColor: SubjectsColors = SubjectsColors.PurpleGradient,
    homeScreenEvent: (HomeScreenEvents) -> Unit,

    ){
    LazyRow(
        horizontalArrangement= Arrangement.spacedBy(dimensionResource(R.dimen.add_subject_dialoge_spacedby))
    ) {
        items(colorsList){color ->
            DialogBoxColorItem(color ,
                { homeScreenEvent(HomeScreenEvents.SubjectColorSelected(color)) }, selectedColor)
        }
    }

}