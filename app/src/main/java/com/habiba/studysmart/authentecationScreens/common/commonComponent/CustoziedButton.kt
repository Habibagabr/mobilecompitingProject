package com.habiba.studysmart.authentecationScreens.common.commonComponent

import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.habiba.studysmart.R

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.habiba.studysmart.ui.theme.blueGradient
import com.habiba.studysmart.ui.theme.constantGray


@Composable
fun CustomizedButton(
    onClick: () -> Unit,
    text: String,
    pressingState: Boolean = true
) {

    val gradientBrush = Brush.verticalGradient(blueGradient)

    val disabledColor = constantGray

    Box(
        modifier = Modifier
            .background(brush =
                if(pressingState) gradientBrush else Brush.verticalGradient(listOf(disabledColor, disabledColor)) , shape = RoundedCornerShape(dimensionResource(R.dimen.authentication_btn_shape)))
            .width(dimensionResource(R.dimen.authentication_btn_size))
            .clickable{ onClick() }
            .padding(dimensionResource(R.dimen.authen_btn_space))
            ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall.copy(fontSize = 22.sp),
            color = if(pressingState) Color.White  else Color.White.copy(0.5f),
            textAlign = TextAlign.Center,
        )
    }
}
