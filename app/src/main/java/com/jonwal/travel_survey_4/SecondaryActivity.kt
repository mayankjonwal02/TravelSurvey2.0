package com.jonwal.travel_survey_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.jonwal.travel_survey_4.navigation.navgraph
import com.jonwal.travel_survey_4.navigation.screens
import com.jonwal.travel_survey_4.screens.dayOneForm
import com.jonwal.travel_survey_4.ui.theme.Travel_survey_4Theme

class SecondaryActivity :  ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Travel_survey_4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navgraph(start = screens.dayform.route)
                }
            }
        }
    }
}