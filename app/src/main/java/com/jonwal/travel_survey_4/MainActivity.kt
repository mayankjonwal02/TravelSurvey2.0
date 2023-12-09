package com.jonwal.travel_survey_4

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jonwal.travel_survey_4.navigation.navgraph
import com.jonwal.travel_survey_4.navigation.screens
import com.jonwal.travel_survey_4.screens.Disclaimer
import com.jonwal.travel_survey_4.screens.dayOneForm
//import com.jonwal.travel_survey_4.screens.loc
//import com.jonwal.travel_survey_4.screens.onetimeform
import com.jonwal.travel_survey_4.ui.theme.Travel_survey_4Theme
import onetimeform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Travel_survey_4Theme {
                // A surface container using the 'background' color from the theme
                // Check for permission
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    // Permission is already granted
                } else {
                    // Request permission
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),1)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navgraph(screens.splash.route)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Travel_survey_4Theme {
        Greeting("Android")
    }
}