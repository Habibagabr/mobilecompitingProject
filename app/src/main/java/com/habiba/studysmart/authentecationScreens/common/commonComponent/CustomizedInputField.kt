package com.habiba.studysmart.authentecationScreens.common.commonComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.habiba.studysmart.R
import com.habiba.studysmart.authentecationScreens.InputFieldType
import com.habiba.studysmart.common.strings.EMPTY_STRING

@Composable
fun CustomizedInputField(
    inputType: InputFieldType,
    input :String,
    placeholder:String,
    label:String,
    onValueChange: (String) -> Unit,
    errorMsg:String,
    error :Boolean
){

    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.input_error_space)),
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = { newValue -> onValueChange(newValue)},
            shape = RoundedCornerShape(dimensionResource(R.dimen.round_inputField_corner_shape)),
            singleLine = true,
            placeholder = {Text(text = placeholder)},
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (inputType == InputFieldType.Password || inputType == InputFieldType.ConfirmPassword) {
                    val image = if (passwordVisible) {
                        painterResource(R.drawable.hidden)

                    } else {
                        painterResource(R.drawable.eye)

                    }
                    Icon(
                        painter = image,
                        contentDescription = EMPTY_STRING,
                        modifier = Modifier.clickable { passwordVisible = !passwordVisible }
                    )
                }
            },
            visualTransformation= if ((inputType == InputFieldType.Password || inputType == InputFieldType.ConfirmPassword) && !passwordVisible)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
        )
        if(error) {
            Text(
                text = errorMsg,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }


    }
}