package com.jonwal.travel_survey_4.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonwal.travel_survey_4.navigation.screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(mynavHostController: NavHostController) {
    // Create a Column layout with a white background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Create a Box for the "Travel Survey" text with a larger font size
        Box(
            modifier = Modifier
                .background(Color.White)
        ) {
            Text(
                text = "Travel Survey",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }

    var context = LocalContext.current

    LaunchedEffect(Unit)
    {

        var formfilled = getsharedpref(context).getBoolean("formfilled",false)
        delay(2000)
        if(formfilled)
        {
            if(isservicerunning(context))
            {
                mynavHostController.navigate(screens.servicerunning.route)
            }
            else
            {
                mynavHostController.navigate(screens.startservice.route)
            }
        }
        else
        {
            mynavHostController.navigate(screens.disclaimer.route)
        }

    }
}


fun getsharedpref(context: Context): SharedPreferences{
    return context.getSharedPreferences("mysharedprefs",Context.MODE_PRIVATE)
}