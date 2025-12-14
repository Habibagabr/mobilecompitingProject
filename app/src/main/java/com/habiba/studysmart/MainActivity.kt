package com.habiba.studysmart

import NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.habiba.studysmart.subjectScreen.ui.SubjectScreen
import com.habiba.studysmart.ui.theme.StudySmartTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudySmartTheme {
                    NavGraph()
//                SubjectScreen()

            }
        }
    }
}

