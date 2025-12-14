package com.habiba.studysmart.homeScreen.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.habiba.studysmart.R
import com.habiba.studysmart.common.components.EmptySection
import com.habiba.studysmart.common.components.SectionHeader
import com.habiba.studysmart.domain.model.SubjectDomainModel
import com.habiba.studysmart.homeScreen.ui.components.homeScreenComponents.SubjectCard


@Composable
fun SubjectsSection(
    subjectsList: List<SubjectDomainModel>,
    onAddSubjectClicked: () -> Unit,
    onSubjectClicked:(SubjectDomainModel)->Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.subject_section_spacedBy))
    ){
        SubjectsSectionHeader { onAddSubjectClicked() }
        if(subjectsList.isNotEmpty()) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.card_spacedby))
            ) {
                items(subjectsList){ subject ->
                    SubjectCard(
                        subjectTitle = subject.name,
                        cardBackgroundColor = subject.colorHex.color,
                        onSubjectClicked= { onSubjectClicked(subject) }
                    )


                }

            }
        }
        else{
            EmptySection(
                emptyImage = R.drawable.img_books,
                emptyText = stringResource(R.string.add_subject_text)
            )
        }

    }

}

@Composable
fun SubjectsSectionHeader(onAddSubjectClicked: () -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SectionHeader(stringResource(R.string.subject_header))

        IconButton(
            onClick = {
                onAddSubjectClicked()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_add_24),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = stringResource(R.string.add_subject_button)
            )
        }
    }

}