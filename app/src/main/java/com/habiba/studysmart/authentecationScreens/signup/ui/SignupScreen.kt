package com.habiba.studysmart.authentecationScreens.signup.ui

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
import com.habiba.studysmart.authentecationScreens.commonComponent.AuthenticationScreenTemplate
import com.habiba.studysmart.authentecationScreens.commonComponent.CustomizedInputField
import com.habiba.studysmart.navigation.LoginScreen

@Composable
fun SignupScreen(navController: NavController) {
    AuthenticationScreenTemplate(
        screenTitle = stringResource(R.string.auth_Sign_up_welcome),
        screenSubTitle = stringResource(R.string.auth_sign_up_welcome_sub),
        btnText = stringResource(R.string.sign_up),
        alternativeOption = stringResource(R.string.alternative_log_in),
        subBtnTxt = stringResource(R.string.log_in),
        onSubBtnClicked = { navController.navigate(LoginScreen) }


        ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.text_field_space)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.text_field_space))
        ) {
            CustomizedInputField(
                label = stringResource(R.string.user_name_label),
                placeholder = stringResource(R.string.user_name_placeholder),
                inputFieldType = InputFieldType.Name

            )

            CustomizedInputField(
                label = stringResource(R.string.email),
                placeholder = stringResource(R.string.email_placeholder),
                inputFieldType = InputFieldType.Email

            )

            CustomizedInputField(
                label = stringResource(R.string.password),
                placeholder = stringResource(R.string.password_placeholder),
                inputFieldType = InputFieldType.Password
            )
            CustomizedInputField(
                label = stringResource(R.string.confirm_password),
                placeholder = stringResource(R.string.confirm_password_placeholder),
                inputFieldType = InputFieldType.ConfirmPassword
            )

        }

    }

}