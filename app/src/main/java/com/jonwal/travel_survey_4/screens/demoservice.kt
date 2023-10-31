package com.jonwal.travel_survey_4.screens

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.jonwal.travel_survey_4.locationService

@Composable
fun loc() {
    var context = LocalContext.current
    var intent = Intent(context,locationService::class.java)
    context.startForegroundService(intent)
}