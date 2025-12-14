package com.habiba.studysmart.sessionScreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.habiba.studysmart.R
import com.habiba.studysmart.sessionScreen.utils.BtnTypes
import com.habiba.studysmart.ui.theme.Typography

@Composable
fun TimerBtn(
    btnTypes: BtnTypes,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(dimensionResource(R.dimen.small_btn_height))
            .width(dimensionResource(R.dimen.small_btn_width))
            .background(
                color = when {
                    !enabled ->
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)

                    btnTypes == BtnTypes.STOP ->
                        MaterialTheme.colorScheme.errorContainer

                    else ->
                        MaterialTheme.colorScheme.primary
                },
                shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius))
            )
            .clickable(enabled = enabled, onClick = onClick)
            .padding(dimensionResource(R.dimen.btn_padding)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = btnTypes.btnText,
            style = Typography.titleMedium.copy(fontWeight = FontWeight.Medium),
            color = if(enabled) Color.White else Color.White.copy(alpha = 0.2f)
        )
    }
}
