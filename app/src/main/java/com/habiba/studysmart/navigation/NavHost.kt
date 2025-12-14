import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.habiba.studysmart.navigation.OnBoardingScreen
import com.habiba.studysmart.navigation.SplashScreen
import com.habiba.studysmart.splashScreen.ui.SplashScreen
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.toRoute
import com.habiba.studysmart.authentecationScreens.login.ui.LoginScreen
import com.habiba.studysmart.authentecationScreens.login.viewModel.LoginViewModel
import com.habiba.studysmart.onboarding.ui.OnBoardingScreen
import com.habiba.studysmart.onboarding.ui.viewModel.OnBoardingViewModel
import com.habiba.studysmart.authentecationScreens.signup.ui.SignupScreen
import com.habiba.studysmart.authentecationScreens.signup.viewModel.SignupViewModel
import com.habiba.studysmart.homeScreen.ui.HomeScreen
import com.habiba.studysmart.homeScreen.ui.homeScreenViewModel.HomeScreenViewModel
import com.habiba.studysmart.navigation.Home
import com.habiba.studysmart.navigation.LoginScreen
import com.habiba.studysmart.navigation.SessionScreen
import com.habiba.studysmart.navigation.SignupScreen
import com.habiba.studysmart.navigation.SubjectScreen
import com.habiba.studysmart.navigation.TaskScreen
import com.habiba.studysmart.sessionScreen.ui.SessionScreen
import com.habiba.studysmart.sessionScreen.ui.viewModel.SessionScreenViewModel
import com.habiba.studysmart.splashScreen.viewModel.SplashScreenViewModel
import com.habiba.studysmart.subjectScreen.ui.SubjectScreen
import com.habiba.studysmart.subjectScreen.ui.viewModel.SubjectScreenViewModel
import com.habiba.studysmart.taskScreen.ui.TaskScreen
import com.habiba.studysmart.taskScreen.ui.viewModel.TaskScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashScreen,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth },
            )
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }
            ,
            )
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth },
            )
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth },
            )
        }
    ) {

        composable<SplashScreen> {
            val splashScreenViewModel : SplashScreenViewModel = hiltViewModel()
            val splashScreenState = splashScreenViewModel.state
            val state by splashScreenState.collectAsState()
            SplashScreen(
                navController,
                state = state,
                onEvent = splashScreenViewModel::onEvent
            )
        }

        composable<OnBoardingScreen> {
            val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
            val onBoardingState = onBoardingViewModel.onBoardingState
            val state by onBoardingState.collectAsState()
            OnBoardingScreen(
                navController = navController,
                state=state,
                onEvent = onBoardingViewModel::onEvent
            )
        }
        composable<LoginScreen> {
            val loginViewModel: LoginViewModel = hiltViewModel()
            val loginState = loginViewModel.loginState
            val state by loginState.collectAsState()
           LoginScreen(
               navController=navController,
               state= state,
               onEvent = loginViewModel::onEvents

           )
        }
        composable<SignupScreen> {
            val signupViewModel : SignupViewModel = hiltViewModel()
            val signupState = signupViewModel.signupState
            val state by signupState.collectAsState()
            SignupScreen(
               navController= navController,
                state= state,
                onEvent = signupViewModel::onEvent
            )
        }

        composable<Home> {
            val homeViewModel : HomeScreenViewModel = hiltViewModel()
            val homeState = homeViewModel.homeScreenState
            val state by homeState.collectAsState()
            HomeScreen(
                navController = navController,
                homeScreenState = state ,
                homeScreenEvents = homeViewModel::onEvent
            )

        }

        composable<SubjectScreen>{ backStackEntry->
            val arg = backStackEntry.toRoute<SubjectScreen>()
            val subjectId = arg.subjectId

            val screenViewModel : SubjectScreenViewModel = hiltViewModel()
            val screenState = screenViewModel.state
            val state by screenState.collectAsState()

            SubjectScreen(
                state = state,
                events = screenViewModel::onEvent,
                navController = navController

            )

        }

        composable<TaskScreen> { backStackEntry ->
            val arg = backStackEntry.toRoute<TaskScreen>()
            val subjectId = arg.subjectId
            val subjectName= arg.subjectName

            val viewModel : TaskScreenViewModel = hiltViewModel()
            val screenState = viewModel.state
            val state by screenState.collectAsState()
            TaskScreen(
                subjectId= subjectId,
                state=state,
                onEvent = viewModel::onEvent,
                navController = navController
            )

        }

        composable<SessionScreen>{backStackEntry ->
            val arg = backStackEntry.toRoute<SessionScreen>()
            val userId = arg.userId

            val viewModel : SessionScreenViewModel = hiltViewModel()
            val screenState = viewModel.state
            val state by screenState.collectAsState()

            SessionScreen(
                navController = navController,
                state=state,
                onEvent = viewModel::onEvent

            )


        }

    }
}