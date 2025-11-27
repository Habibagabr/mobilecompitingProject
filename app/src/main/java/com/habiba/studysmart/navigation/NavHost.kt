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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.habiba.studysmart.authentecationScreens.login.ui.LoginScreen
import com.habiba.studysmart.authentecationScreens.login.ui.viewModel.LoginViewModel
import com.habiba.studysmart.onboarding.ui.OnBoardingScreen
import com.habiba.studysmart.onboarding.ui.viewModel.OnBoardingViewModel
import com.habiba.studysmart.authentecationScreens.signup.ui.SignupScreen
import com.habiba.studysmart.authentecationScreens.signup.ui.viewModel.SignupViewModel
import com.habiba.studysmart.navigation.LoginScreen
import com.habiba.studysmart.navigation.SignupScreen

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
            SplashScreen(navController)
        }
        composable<OnBoardingScreen> {
            val onBoardingViewModel: OnBoardingViewModel = viewModel()
            val onBoardingState = onBoardingViewModel.onBoardingState
            val state by onBoardingState.collectAsState()
            OnBoardingScreen(
                navController = navController,
                state=state,
                onEvent = onBoardingViewModel::onEvent
            )
        }
        composable<LoginScreen> {
            val loginViewModel: LoginViewModel = viewModel()
            val loginState = loginViewModel.loginState
            val state by loginState.collectAsState()
           LoginScreen(
               navController=navController,
               state= state,
               onEvent = loginViewModel::onEvents

           )
        }
        composable<SignupScreen> {
            val signupViewModel : SignupViewModel = viewModel()
            val signupState = signupViewModel.signupState
            val state by signupState.collectAsState()
            SignupScreen(
               navController= navController,
                state= state,
                onEvent = signupViewModel::onEvent
            )
        }


    }
}