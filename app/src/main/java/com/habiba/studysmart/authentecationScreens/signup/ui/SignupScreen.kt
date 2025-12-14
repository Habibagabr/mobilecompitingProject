package com.habiba.studysmart.authentecationScreens.signup.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.habiba.studysmart.R
import com.habiba.studysmart.authentecationScreens.InputFieldType
import com.habiba.studysmart.authentecationScreens.common.commonComponent.AuthenticationScreenTemplate
import com.habiba.studysmart.common.components.CustomizedInputField
import com.habiba.studysmart.authentecationScreens.signup.viewModel.SignupScreenEvents
import com.habiba.studysmart.authentecationScreens.signup.viewModel.SignupScreenState
import com.habiba.studysmart.common.components.PopupDialog
import com.habiba.studysmart.navigation.Home
import com.habiba.studysmart.navigation.LoginScreen
import com.habiba.studysmart.navigation.OnBoardingScreen
import kotlinx.coroutines.delay

@Composable
fun SignupScreen(
    navController: NavController,
    state : SignupScreenState,
    onEvent: (SignupScreenEvents) -> Unit
) {
    LaunchedEffect(state.success) {
        if (state.success) {

            delay(1500)

            onEvent(SignupScreenEvents.SuccessSignUp(state.uid))

            navController.navigate(Home) {
                popUpTo(OnBoardingScreen) { inclusive = true }
            }
        }
    }
        PopupDialog(
            isDialogOpened = state.success,
            title = R.string.signup_success,
            titleIcon = R.drawable.check
        ) {

        }

    AuthenticationScreenTemplate(
        screenTitle = stringResource(R.string.auth_Sign_up_welcome),
        screenSubTitle = stringResource(R.string.auth_sign_up_welcome_sub),
        btnText = stringResource(R.string.sign_up),
        alternativeOption = stringResource(R.string.alternative_log_in),
        subBtnTxt = stringResource(R.string.log_in),
        onSubBtnClicked = { navController.navigate(LoginScreen){
            popUpTo(OnBoardingScreen) { inclusive = false }

        } },
        onBtnClicked={onEvent(SignupScreenEvents.SignupPressed)},
        generalErrorMsg = state.generalError,
        isGeneralError = state.isGeneralError
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.text_field_space)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.text_field_space))
        ) {
            CustomizedInputField(
                label = stringResource(R.string.user_name_label),
                placeholder = stringResource(R.string.user_name_placeholder),
                input = state.userName,
                onValueChange = {newChar ->
                    onEvent(SignupScreenEvents.UserNameChanged(newChar))},
                inputType = InputFieldType.Name,
                errorMsg = state.userNameError,
                error = !state.isUserNameValid
            )

            CustomizedInputField(
                label = stringResource(R.string.email),
                placeholder = stringResource(R.string.email_placeholder),
                onValueChange = {
                    newChar ->
                    onEvent(SignupScreenEvents.EmailChanged(newChar))
                },
                input = state.userEmail,
                inputType = InputFieldType.Email,
                errorMsg = state.emailError,
                error = !state.isEmailValid

            )

            CustomizedInputField(
                label = stringResource(R.string.password),
                placeholder = stringResource(R.string.password_placeholder),
                onValueChange = {
                    newChar ->
                    onEvent(SignupScreenEvents.PasswordChanged(newChar))
                },
                input = state.userPassword,
                inputType = InputFieldType.Password,
                errorMsg = state.passwordError,
                error = !state.isPasswordValid
            )
            CustomizedInputField(
                label = stringResource(R.string.confirm_password),
                placeholder = stringResource(R.string.confirm_password_placeholder),
                onValueChange = {
                        newChar ->
                    onEvent(SignupScreenEvents.ConfirmPasswordChanged(newChar))
                },
                input = state.confirmPassword,
                inputType = InputFieldType.ConfirmPassword,
                errorMsg = state.confirmPasswordError,
                error = !state.isConfirmPasswordValid

            )

        }

    }

}