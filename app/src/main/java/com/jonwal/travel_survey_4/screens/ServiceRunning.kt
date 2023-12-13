package com.jonwal.travel_survey_4.screens

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jonwal.travel_survey_4.locationService
import com.jonwal.travel_survey_4.navigation.screens

@Composable
fun ServiceRunning(mynavHostController: NavHostController)
{
    // Create a TextFieldValue for the input field


    // Create a Column layout with a white background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display "Service Running" in blue color and larger text
        Text(
            text = "Tracking .......",
            fontSize = 24.sp,
            color = Color.Blue
        )

        // Create a BasicTextField for user input
        Spacer(modifier = Modifier.height(30.dp))

        var context = LocalContext.current
        // Create a button with blue background and white text
        Button(
            onClick = {
                if(isservicerunning(context))
                {
                    var intent = Intent(context , locationService::class.java)
                    context.stopService(intent)
                    getsharedpref(context).edit().putBoolean("isservicerunning",false).apply()
                    mynavHostController.navigate(screens.startservice.route)
                }

            },
            modifier = Modifier
                , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White)
        ) {
            Text(
                text = "Stop Saving Location Data",

            )
        }
    }
    Box(modifier = Modifier

        .fillMaxSize()
        .background(Color.Transparent), contentAlignment = Alignment.BottomEnd)
    {
        Button(
            onClick = {


                mynavHostController.navigate(screens.dayform.route)

            },
            modifier = Modifier.padding(end = 10.dp)
            , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White),

            ) {
            Text(
                text = "Start Survey",

                )
        }
    }


}

@Composable
fun startservice(mynavHostController: NavHostController)
{
    // Create a TextFieldValue for the input field
    var context = LocalContext.current

    // Create a Column layout with a white background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp) , horizontalAlignment = Alignment.Start){
            Text(
                text = "Instructions for completing the survey:",
                color = if(isSystemInDarkTheme()){
                    Color.Blue}else{ Color.Black },
                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "1. Kindly keep your mobile internet and location on.",
                color = if(isSystemInDarkTheme()){
                    Color.Blue}else{ Color.Black },
                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(top = 10.dp , bottom = 10.dp , start = 30.dp, end = 30.dp)
            )
            Text(
                text = "2. Please click on saving the location data and keep this app running in the background during your trips",
                color = if(isSystemInDarkTheme()){
                    Color.Blue}else{ Color.Black },                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(top = 10.dp , bottom = 10.dp , start = 30.dp, end = 30.dp)
            )
            Text(
                text = "3. A Trip is defined as a one-way course of travel with a single main/primary purpose. Origin is defined as the start of the trip and Destination as the end of the trip.",
                color = if(isSystemInDarkTheme()){
                    Color.Blue}else{ Color.Black },                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(top = 10.dp , bottom = 10.dp , start = 30.dp, end = 30.dp)
            )
            Text(
                text = "4. A Trip Chain happens when you make more than one stop with any other purpose than the main purpose.",
                color = if(isSystemInDarkTheme()){
                    Color.Blue}else{ Color.Black },
                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(top = 10.dp , bottom = 10.dp , start = 30.dp, end = 30.dp)
            )
            Text(
                text = "5. A Trip Leg is a Trip made between two consecutive stops. You need to fill information about your daily travel  (Mainly commute) for the next few days. You can fill up to 7 trips per day based on the importance of the trip. ",
                color = if(isSystemInDarkTheme()){
                    Color.Blue}else{ Color.Black },
                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(top = 10.dp , bottom = 10.dp , start = 30.dp, end = 30.dp)
            )
            Text(
                text = "6. Kindly click on Start Filling Trip information to fill in the trip details. ",
                color = if(isSystemInDarkTheme()){
                    Color.Blue}else{ Color.Black },
                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(top = 10.dp , bottom = 10.dp , start = 30.dp, end = 30.dp)
            )
            Text(
                text = "7. The Satisfaction Level chosen will be defined in Feedback after selection. ",
                color = if(isSystemInDarkTheme()){
                    Color.Blue}else{ Color.Black },
                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(top = 10.dp , bottom = 10.dp , start = 30.dp, end = 30.dp)
            )
            Text(
                text = "We appreciate you taking part in this survey. ",
                color = if(isSystemInDarkTheme()){
                    Color.Blue}else{ Color.Black },
                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(top = 10.dp , bottom = 10.dp , start = 30.dp , end = 30.dp),
                fontStyle = FontStyle.Italic
            )
        }
        // Display "Service Running" in blue color and larger text

        // Create a BasicTextField for user input
        Spacer(modifier = Modifier.height(30.dp))

        // Create a button with blue background and white text
        Button(
            onClick = {

                if(!isservicerunning(context)){
                    var intent = Intent(context, locationService::class.java)
                    context.startForegroundService(intent)
                    getsharedpref(context).edit().putBoolean("isservicerunning",true).apply()
                }
                mynavHostController.navigate(screens.servicerunning.route)

            },
            modifier = Modifier
            , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White)
        ) {
            Text(
                text = "Start Saving Location Data",

                )
        }

        Button(
            onClick = {


                mynavHostController.navigate(screens.dayform.route)

            },
            modifier = Modifier.padding(end = 10.dp)
            , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White),

            ) {
            Text(
                text = "Start Survey",

                )
        }

    }
//    Box(modifier = Modifier
//
//        .fillMaxSize()
//        .background(Color.Transparent), contentAlignment = Alignment.BottomEnd)
//    {
//        Button(
//            onClick = {
//
//
//                mynavHostController.navigate(screens.dayform.route)
//
//            },
//            modifier = Modifier.padding(end = 10.dp)
//            , colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue, contentColor = Color.White),
//
//            ) {
//            Text(
//                text = "Start Survey",
//
//                )
//        }
//    }


}

fun isservicerunning(context : Context): Boolean {
    var isservicerunning = getsharedpref(context).getBoolean("isservicerunning",false)
    return isservicerunning
}