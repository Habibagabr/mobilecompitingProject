package com.habiba.studysmart.authentecationScreens.commonComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.habiba.studysmart.R
import com.habiba.studysmart.authentecationScreens.InputFieldType

@Composable
fun CustomizedInputField(
    inputFieldType: InputFieldType,
    placeholder:String,
    label:String
){
    OutlinedTextField(
        value = "",
        onValueChange = {},
        shape = RoundedCornerShape(dimensionResource(R.dimen.round_inputField_corner_shape)),
        singleLine = true,
        placeholder = {Text(text = placeholder)},
        label = { Text(text = label) },
        modifier = Modifier.fillMaxWidth(),

    )
}