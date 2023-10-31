package com.jonwal.travel_survey_4.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jonwal.travel_survey_4.screens.*
import onetimeform

@Composable
fun navgraph(start : String) {

    var mynavHostController = rememberNavController()
    NavHost(navController = mynavHostController, startDestination = start )
    {
        composable(screens.splash.route)
        {
            SplashScreen(mynavHostController)
        }
        composable(screens.disclaimer.route)
        {
            Disclaimer(mynavHostController)
        }

        composable(screens.onetimeform.route)
        {
            onetimeform(mynavHostController)
        }

        composable(screens.servicerunning.route)
        {
            ServiceRunning(mynavHostController)
        }

        composable(screens.startservice.route)
        {
            startservice(mynavHostController)
        }
        composable(screens.dayform.route)
        {
            dayOneForm(mynavHostController)
        }

        composable(screens.thankyou.route)
        {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.Center)
            {
                Text(text = "Thank You", color = Color.Blue , style = MaterialTheme.typography.h5, fontWeight = FontWeight.ExtraBold)
            }
        }

    }
}