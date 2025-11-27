package com.habiba.studysmart.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.habiba.studysmart.R

@Composable
fun EmptySection(
    emptyImage:Int=R.drawable.img_books,
    emptyText:String=stringResource(R.string.add_subject_text),

){
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.empty_section_vertical_padding))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(emptyImage),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(R.dimen.empty_image_size))
        )
        Spacer(
            modifier = Modifier.height(dimensionResource(R.dimen.empty_image_text_space))
        )
        Text(
            text = emptyText,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

    }
}