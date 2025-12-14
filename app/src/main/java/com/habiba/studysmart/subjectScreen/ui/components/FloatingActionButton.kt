package com.habiba.studysmart.subjectScreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.habiba.studysmart.R
import com.habiba.studysmart.ui.theme.Typography

@Composable
fun SubjectScreenFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.app_horizontal_padding)),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(R.drawable.baseline_add_24), null)
            Text(
                text = stringResource(R.string.add_new_task),
                style = Typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
