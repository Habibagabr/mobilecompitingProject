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
import com.habiba.studysmart.authentecationScreens.commonComponent.AuthenticationScreenTemplate
import com.habiba.studysmart.authentecationScreens.commonComponent.CustomizedInputField
import com.habiba.studysmart.navigation.SignupScreen

@Composable
fun LoginScreen(navController: NavController) {
    AuthenticationScreenTemplate(
        screenTitle = stringResource(R.string.auth_welcome),
        screenSubTitle = stringResource(R.string.auth_welcome_sub),
        btnText = stringResource(R.string.log_in),
        alternativeOption = stringResource(R.string.alternative_sign_up),
        subBtnTxt = stringResource(R.string.sign_up),
        onSubBtnClicked = { navController.navigate(SignupScreen) }

    ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.text_field_space))
            ,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.text_field_space))
        ) {
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
        }

    }

}