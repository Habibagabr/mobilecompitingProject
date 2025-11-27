package com.habiba.studysmart.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.habiba.studysmart.R

@Composable
fun SectionHeader(sectionTitle:String){
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.subject_section_spacedBy))

    ) {
        Text(
            text= sectionTitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

}