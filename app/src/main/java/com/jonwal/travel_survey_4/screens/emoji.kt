package com.jonwal.travel_survey_4.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun KindRadioGroupUsage(question : String ,feedback: String, setSelected: (String) -> Unit) {
    val kinds = mapOf<String,String>(
        "VeryDissatisfied" to "\uD83D\uDE1E",
        "Dissatisfied" to  "\uD83D\uDE1F",
        "SomewhatDissatisfied" to  "\uD83D\uDE10",
        "SomewhatDissatisfied" to "\uD83D\uDE10",
        "Neutral" to  "\uD83D\uDE10" , // Using the same as somewhat dissatisfied for neutral
        "SomewhatSatisfied" to  "\uD83D\uDE0A",
        "Satisfied" to  "\uD83D\uDE03",
        "VerySatisfied" to "\uD83D\uDE04"
    )

    Column (
        Modifier
            .background(Color.White)
            .padding(top = 20.dp)){
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = question,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            color =if(isSystemInDarkTheme()){
                Color.Blue}else{ Color.Black },

            )
        KindRadioGroup(
            mItems = kinds.entries.reversed().reversed(),
            feedback, setSelected
        )
        Text(
            text = "Feedback : $feedback",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            color = if(isSystemInDarkTheme()){
                Color.Blue}else{ Color.Black }, fontWeight = FontWeight.ExtraBold,

        )
    }
}

@Composable
fun KindRadioGroup(
    mItems: List<Map.Entry<String, String>>,
    selected: String,
    setSelected: (selected: String) -> Unit,
) {
//    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically


        ) {
            mItems.forEach { item ->
                Column(

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 10.dp)
                ) {
                    Text(text = item.value, fontSize = 30.sp, modifier = Modifier.padding(3.dp), color =if(isSystemInDarkTheme()){
                        Color.Blue}else{ Color.Black }, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(5.dp))
                    RadioButton(
                        selected = selected == item.key,
                        onClick = {
                            setSelected(item.key)
                        },
                        enabled = true,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Magenta
                        )
                    )

                }
            }
        }
//    }
}