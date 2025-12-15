package com.habiba.studysmart.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin

@Composable
fun ScreensTransition(
    visible: Boolean =false,
    content: @Composable ()-> Unit,

    ){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var visibalityOfContent = false
        if (visible){
            visibalityOfContent = true
        }
        AnimatedVisibility(
            visible = visibalityOfContent,
            modifier = Modifier.fillMaxSize(),
            enter = scaleIn(
                animationSpec= TweenSpec(
                    durationMillis = 500
                ),
                initialScale=0.5f,
                transformOrigin= TransformOrigin.Center

            )
        ) {
            content()
        }

    }










}