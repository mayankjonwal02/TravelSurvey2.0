package com.jonwal.travel_survey_4.screens

import android.app.Activity
import android.app.Notification.Action
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.jonwal.travel_survey_4.MainActivity
import com.jonwal.travel_survey_4.R
import com.jonwal.travel_survey_4.SecondaryActivity
import com.jonwal.travel_survey_4.navigation.screens

@Composable
fun Disclaimer(mynavHostController: NavHostController) {
    var context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = 2.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                    textAlign = TextAlign.Center,
                    text = "Disclaimer",
                    style = MaterialTheme.typography.h5,
                    color = if(isSystemInDarkTheme()){
                        Color.Blue}else{ Color.Black },
                    fontWeight = FontWeight.ExtraBold
                )
                Column(modifier = Modifier.verticalScroll(rememberScrollState())){
                    Text(
                        text = "Dear Respondent,",
                        style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text ="Thank you for installing the application and taking part in this study." ,
                        style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Please follow the guidelines given by the Surveyor.",
                        style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    var a = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 16.sp,   color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },)) {
                            append("The app aims to understand the travel behaviour in Jodhpur city as part of an ongoing research at the  ")
                        }
                        withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.ExtraBold , color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },)) {
                            append("Indian Institute of Technology Jodhpur")
                        }
                    }
                    Text(text = a,  color = if(isSystemInDarkTheme()){
                        Color.Blue}else{ Color.Black },)
//                    Text(
//                            text = "The app aims to understand the travel behaviour in Jodhpur city as part of an ongoing research at the  Indian Institute of Technology Jodhpur.",
//                    style = MaterialTheme.typography.body1,
//                    color = Color.Black
//                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                    text ="The app needs permission to collect the location data of your smartphone." ,
                    style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },                )
                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "We respect your privacy",
                    style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
                     fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                    text = "We uphold the ethical principles of respecting your personal information and handling your data. Your trust is our utmost priority. The data collected in this study will be used for academic purposes only and will not be shared with anyone other than the research team.\n",
                    style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
                )
                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text ="We request that you keep the app running for the time of the survey, especially during your daily travel. The study will be conducted in two parts." ,
                    style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                    text ="- Collect the location data using this application for the next ten days and some preliminary questions taking 3-5 mins of your time daily." ,
                    style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
                )
                    Text(
                        text ="- A questionnaire-based survey self administered or by the surveyor, taking 10-15 mins of your time.",
                    style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
                    )
                    Spacer(modifier = Modifier.height(15.dp))


                    Text(
                        text = "Your participation is voluntary, and we do not collect any personally identifiable information as part of this survey. Your responses are confidential, and your identity will never be public.",
                        style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
//                        , fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(3.dp))


                    Text(
                        text = "For any questions, please feel free to contact Dr Ranju Mohan at ranju@iitj.ac.in or Shahiq Wani at wani.1@iitj.ac.in",
                        style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
//                        , fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(3.dp))


                    TextButton(onClick = {
                        var intent = Intent(Intent.ACTION_VIEW , Uri.parse("https://docs.google.com/document/d/e/2PACX-1vQH8or-oWvtAlJgjsjv6pdneMG09snfrQ2uqScQpHbstrun9u3ySZGUpKd_nVYd3_zB_p8lLj9sXUZK/pub"))

                        context.startActivity(intent)

                    },
                    colors = ButtonDefaults.textButtonColors(backgroundColor = Color.White , contentColor = Color.Magenta)) {

                        Text(text = "Privacy Policy", color = Color.Magenta)
                    }
                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = "Please Select “Accept” if you read the information above and agree to participate in this study.",
                        style = MaterialTheme.typography.body1,
                        color = if(isSystemInDarkTheme()){
                            Color.Blue}else{ Color.Black },
//                        , fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    var context = LocalContext.current
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        OutlinedButton(onClick = {
                            var channel = NotificationChannel(
                                "Turn on GPS",
                                "Turn on GPS",
                                NotificationManager.IMPORTANCE_DEFAULT
                            )
                            var notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                            notificationManager.createNotificationChannel(channel)


                            var notification = NotificationCompat.Builder(context , "Turn on GPS")
                                .setContentTitle("Turn on GPS")
                                .setContentText("Kindly turn on your GPS")
                                .setSmallIcon(R.drawable.location)

                                .setColor(ContextCompat.getColor(context, R.color.teal_200))
                                .setColorized(true)
                                .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)

                            notificationManager.notify(1003, notification.build())

                                                 mynavHostController.navigate(screens.onetimeform.route)
                                                  }, modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp), colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White, contentColor = Color.Blue), border = BorderStroke(2.dp,
                            Color.Blue)
                        ) {
                            Text(text = "Accept")
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        OutlinedButton(onClick = {

                            finishAffinity(Activity())
                            System.exit(0)
                                                 }, modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp), colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White, contentColor = Color.Blue), border = BorderStroke(2.dp,
                            Color.Blue)
                        ) {
                            Text(text = "Deny")
                        }
                    }
                }

                // Add the rest of your text content here...


            }
        }
    }
}
