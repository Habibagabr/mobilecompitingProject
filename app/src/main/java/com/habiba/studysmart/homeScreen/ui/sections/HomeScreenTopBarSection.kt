package com.habiba.studysmart.homeScreen.ui.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.habiba.studysmart.R
import com.habiba.studysmart.common.components.PopupDialog
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents
import com.habiba.studysmart.navigation.OnBoardingScreen
import com.habiba.studysmart.ui.theme.Typography

@Composable
fun HomeScreenTopBarSection(
    navController: NavController,
    events: (HomeScreenEvents)->Unit
) {
    var isDialogeOpened by remember { mutableStateOf(false) }

    if(isDialogeOpened){
        PopupDialog(
            onDismiss = {
                isDialogeOpened = false
            },
            onConfirm = {
                isDialogeOpened = false
                events(HomeScreenEvents.LogoutConfirmed())
                navController.navigate(OnBoardingScreen) {
                    popUpTo(OnBoardingScreen) {
                        inclusive = true
                    }
                }
            },
            confirmBtnText =R.string.log_out,
            isDialogOpened = true,
            dismissBtnText =R.string.cancel,
            title = R.string.log_out_q,
            titleIcon = R.drawable.logout ,
        )


    }

    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
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
        Box(
            modifier = Modifier
                .size(dimensionResource(R.dimen.add_subject_dialoge_icon_size))
                .clickable {
                    isDialogeOpened=!isDialogeOpened
                }
        ) {
            Icon(
                painter = painterResource(R.drawable.logout),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}

//@Preview
//@Composable
//fun HomeScreenTopBarPreview(){
//    HomeScreenTopBar()
//}