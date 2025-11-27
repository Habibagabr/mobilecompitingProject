package com.habiba.studysmart.authentecationScreens.common.commonComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.habiba.studysmart.ui.theme.constantBlackBackground

@Composable
fun AuthenticationScreenTemplate(
    screenTitle: String,
    screenSubTitle: String,
    btnText: String ,
    onBtnClicked: () -> Unit = {},
    alternativeOption: String ,
    subBtnTxt: String ,
    onSubBtnClicked: () -> Unit = {},
    screenContent: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color=constantBlackBackground)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // ================= TOP CARD =================
            TopAuthBar(
                screenTitle = screenTitle,
                screenSubTitle = screenSubTitle,
            )

            // Screen content from outside
            Column(
                modifier = Modifier.padding(top = 24.dp)
            ) {
                screenContent()
            }

            // ============= BUTTON + ALTERNATIVE OPTION =============
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomizedButton(
                    onClick = onBtnClicked,
                    text = btnText
                )

                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .clickable{onSubBtnClicked()},
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = alternativeOption,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White

                    )
                    Text(
                        text = subBtnTxt,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clickable{onSubBtnClicked()},
                        color = Color.White

                    )
                }
            }
        }
    }
}

