package com.habiba.studysmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.habiba.studysmart.homeScreen.ui.HomeScreen
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenEvents
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenViewModel
import com.habiba.studysmart.ui.theme.StudySmartTheme


class MainActivity : ComponentActivity() {


    /*
    "::" function reference means :
    fun hello(name: String) {
    println("Hello $name")}
    hello("Habiba")  // Output: Hello Habiba
    val ref = ::hello
    ref("Habiba")     // Output: Hello Habiba

    */







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudySmartTheme {

                // we have never pass " view model " as a whole to the screen
                // why ?? as we in this way makes this composable never works except with this view model only
                // " dependency inversion principle violation



                 val homeScreenViewModel: HomeScreenViewModel by viewModels()
                // now we have MutableStateFlow --> kotlin
                val homeScreenStateFlow = homeScreenViewModel.homeScreenState
                // change it to mutableStateOf -->
                // Compose " so collectAsState " must be inside compose function
                // compose only called inside compose function
                val homeScreenState by homeScreenStateFlow.collectAsState()
                // we can't take object from the sealed class as HomeScreenEvents()
                // so we make a function reference to variable " homeScreenEventsHandler "
                // and pass it to the screen
                val homeScreenEventsHandler = homeScreenViewModel::homeScreenEventsHandler

                // passing function which accept HomeScreenEvents as a parameter to the  home screen
                // so we can give it any event " pre instated from the sealed class "


                HomeScreen(
                    homeScreenState = homeScreenState,
                    homeScreenEvents = homeScreenEventsHandler

                )

            }
        }
    }
}

