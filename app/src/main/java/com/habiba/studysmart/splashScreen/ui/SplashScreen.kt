package com.habiba.studysmart.splashScreen.ui

import android.app.Application
import android.content.SharedPreferences
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.habiba.studysmart.navigation.Home
import com.habiba.studysmart.navigation.OnBoardingScreen
import com.habiba.studysmart.navigation.SplashScreen
import com.habiba.studysmart.splashScreen.viewModel.SplashScreenEvents
import com.habiba.studysmart.splashScreen.viewModel.SplashScreenState
import kotlinx.coroutines.delay

// we need to connect this with a view model
@Composable
fun SplashScreen(
    navController: NavController,
    state: SplashScreenState,
    onEvent: (SplashScreenEvents) -> Unit
) {

    var visible by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        visible = true
        onEvent(SplashScreenEvents.Loading)

    }
    LaunchedEffect(key1 = state.userExistence) {
        delay(1000)
        if (state.userExistence) navController.navigate(Home){
            popUpTo(SplashScreen){
                inclusive = true
            }
        }
        else navController.navigate(OnBoardingScreen){
            popUpTo(SplashScreen){
                inclusive = true
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = scaleIn(
                animationSpec = tween(
                    durationMillis = 500,
                    easing = LinearOutSlowInEasing
                ),
                initialScale = 0.5f
            )
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(R.drawable.img_tasks),
                    contentDescription = null,
                    modifier = Modifier.size(dimensionResource(R.dimen.splash_image_size))
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

            }

        }
    }

}