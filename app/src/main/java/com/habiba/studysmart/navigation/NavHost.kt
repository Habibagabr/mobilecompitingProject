import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.habiba.studysmart.navigation.OnBoardingScreen
import com.habiba.studysmart.navigation.SplashScreen
import com.habiba.studysmart.splashScreen.ui.SplashScreen
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.habiba.studysmart.authentecationScreens.login.ui.LoginScreen
import com.habiba.studysmart.authentecationScreens.onboarding.ui.OnBoardingScreen
import com.habiba.studysmart.authentecationScreens.onboarding.ui.viewModel.OnBoardingViewModel
import com.habiba.studysmart.authentecationScreens.signup.ui.SignupScreen
import com.habiba.studysmart.navigation.LoginScreen
import com.habiba.studysmart.navigation.SignupScreen
import kotlin.getValue

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SplashScreen
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
           LoginScreen(
               navController
           )
        }
        composable<SignupScreen> {
            SignupScreen(navController)
        }


    }
}