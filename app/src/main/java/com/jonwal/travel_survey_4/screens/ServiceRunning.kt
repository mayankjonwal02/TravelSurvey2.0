package com.jonwal.travel_survey_4.screens

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
            .background(Color.White),
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
                text = "Stop Service",

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
                text = "Share Trip Experience",

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
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
                text = "Start Survey",

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
                text = "Share Trip Experience",

                )
        }
    }


}

fun isservicerunning(context : Context): Boolean {
    var isservicerunning = getsharedpref(context).getBoolean("isservicerunning",false)
    return isservicerunning
}