package com.habiba.studysmart.homeScreen.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.habiba.studysmart.R
import com.habiba.studysmart.ui.theme.Typography

@Composable
fun HomeScreenTopBarSection(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
        ){
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text= stringResource(R.string.welcome_text),
                style = Typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text= stringResource(R.string.app_name),
                style = Typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground

            )


        }
    }

}

//@Preview
//@Composable
//fun HomeScreenTopBarPreview(){
//    HomeScreenTopBar()
//}