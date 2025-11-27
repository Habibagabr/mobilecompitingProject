package com.habiba.studysmart.onboarding.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.habiba.studysmart.R
import com.habiba.studysmart.authentecationScreens.common.commonComponent.CustomizedButton
import com.habiba.studysmart.onboarding.ui.viewModel.OnBoardingEvents
import com.habiba.studysmart.onboarding.ui.viewModel.OnBoardingState
import com.habiba.studysmart.navigation.LoginScreen
import com.habiba.studysmart.navigation.SignupScreen
import com.habiba.studysmart.ui.theme.constantBlackBackground
import com.habiba.studysmart.ui.theme.constantWhite

@Composable
fun OnBoardingScreen(
    navController: NavController,
    state: OnBoardingState ,
    onEvent: (OnBoardingEvents) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = constantBlackBackground)
            .padding(horizontal = dimensionResource(R.dimen.app_horizontal_padding)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.authen_txt_space))
        ) {
            Image(
                painter = painterResource(R.drawable.planning),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(dimensionResource(R.dimen.onboarding_image_size))
            )
            Text(
                text = stringResource(R.string.onboarding_welcome_text),
                style = MaterialTheme.typography.displaySmall,
                color = constantWhite
            )
            Text(
                text = stringResource(R.string.onboarding_welcome_sub_text),
                style = MaterialTheme.typography.bodyMedium,
                color = constantWhite,
                textAlign = TextAlign.Center
            )

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.authen_btn_space))
            ) {
            CustomizedButton(
                onClick = {
                    onEvent(OnBoardingEvents.LoginPressed)
                    navController.navigate(LoginScreen) },
                text = stringResource(R.string.log_in),
                pressingState = state.loginPressed
            )
            CustomizedButton(
                onClick = {
                    onEvent(OnBoardingEvents.SignUpPressed)
                    navController.navigate(SignupScreen)
                },
                text = stringResource(R.string.sign_up),
                pressingState = state.signUpPressed
            )
        }



    }

}