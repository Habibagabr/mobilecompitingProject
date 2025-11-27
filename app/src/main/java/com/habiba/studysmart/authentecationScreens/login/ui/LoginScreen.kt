package com.habiba.studysmart.authentecationScreens.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.habiba.studysmart.R
import com.habiba.studysmart.authentecationScreens.InputFieldType
import com.habiba.studysmart.authentecationScreens.common.commonComponent.AuthenticationScreenTemplate
import com.habiba.studysmart.authentecationScreens.common.commonComponent.CustomizedInputField
import com.habiba.studysmart.authentecationScreens.login.ui.viewModel.LoginScreenEvents
import com.habiba.studysmart.authentecationScreens.login.ui.viewModel.LoginScreenState
import com.habiba.studysmart.navigation.OnBoardingScreen
import com.habiba.studysmart.navigation.SignupScreen

@Composable
fun LoginScreen(
    navController: NavController,
    state: LoginScreenState ,
    onEvent: (LoginScreenEvents) -> Unit
    ) {
    AuthenticationScreenTemplate(
        screenTitle = stringResource(R.string.auth_welcome),
        screenSubTitle = stringResource(R.string.auth_welcome_sub),
        btnText = stringResource(R.string.log_in),
        alternativeOption = stringResource(R.string.alternative_sign_up),
        subBtnTxt = stringResource(R.string.sign_up),
        onSubBtnClicked = {
            navController.navigate(SignupScreen){
                popUpTo(OnBoardingScreen) { inclusive = false }
            } },
        onBtnClicked={onEvent(LoginScreenEvents.LoginPressed())}

    ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.text_field_space))
            ,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.text_field_space))
        ) {
            CustomizedInputField(
                inputType = InputFieldType.Email,
                input = state.userEmail,
                label = stringResource(R.string.email),
                placeholder = stringResource(R.string.email_placeholder),
                onValueChange = { newChar ->
                    onEvent(LoginScreenEvents.EmailChanged(newChar))
                },
                errorMsg = state.emailError,
                error = !state.isEmailValid

            )
            CustomizedInputField(
                inputType = InputFieldType.Password,
                input= state.userPassword,
                label = stringResource(R.string.password),
                placeholder = stringResource(R.string.password_placeholder),
                onValueChange = {newChar ->
                    onEvent(LoginScreenEvents.PasswordChanged(newChar))
                },
                errorMsg = state.passwordError,
                error = ! state.isPasswordValid
            )
        }

    }

}