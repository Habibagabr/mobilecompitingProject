package com.habiba.studysmart.subjectScreen.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri
import com.habiba.studysmart.R

@Composable
fun SearchOnGoogleFAB(
    subjectName: String
) {
    val context = LocalContext.current

    FloatingActionButton(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = "https://www.google.com/search?q=${Uri.encode(subjectName)}".toUri()
            }
            context.startActivity(intent)
        },
        containerColor = MaterialTheme.colorScheme.secondary
    ) {
        Icon(
            painter = painterResource(R.drawable.search),
            contentDescription = "Search on Google"
        )
    }
}
