package com.habiba.studysmart.homeScreen.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.habiba.studysmart.R
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents

@Composable
fun StartStudySessionSection(
    onBtnClicked:()->Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Button(
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.start_session_horizontal_outer_btn_padding))
                .height(dimensionResource(R.dimen.start_session_btn_height))
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.start_session_horizontal_outer_btn_padding)),
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary),
            onClick = {
                onBtnClicked()
            }
        )
        {
            Text(
                text = stringResource(R.string.start_session_btn),
                style = MaterialTheme.typography.titleMedium,
            )
        }


    }
}
