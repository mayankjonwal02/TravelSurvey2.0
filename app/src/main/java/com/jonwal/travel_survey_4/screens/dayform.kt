package com.jonwal.travel_survey_4.screens

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TimePicker
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.jonwal.travel_survey_4.R
import com.jonwal.travel_survey_4.navigation.screens
import getAndroidID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun dayOneForm(mynavHostController: NavHostController) {
    var day = remember {
        mutableStateOf("")
    }
    var origin1 = remember { mutableStateOf("") }
    val (travelfeedback, travelsetSelected) = remember { mutableStateOf("") }
    val (timefeedback, timesetSelected) = remember { mutableStateOf("") }
    val (costfeedback, costsetSelected) = remember { mutableStateOf("") }
    val (outcomefeedback, outcomesetSelected) = remember { mutableStateOf("") }

    var destination1 = remember { mutableStateOf("") }
    var walked = remember { mutableStateOf("") }
    var walkedtime = remember { mutableStateOf("") }
    var mainTripPurpose1 = remember { mutableStateOf("") }
    var tripChainMade1 =  remember { mutableStateOf("") }
    var tripPurpose1 = remember { mutableStateOf("") }
    var modeOfTravel1 = remember { mutableStateOf("") }

    var householdVehicleUsed1 = remember { mutableStateOf("") }
    var departureTime1 = remember { mutableStateOf("") }
    var arrivalTime1 = remember { mutableStateOf("") }
    var travelCost1 = remember { mutableStateOf("") }
    var duration1 = remember { mutableStateOf("") }

    var tripno = remember {
        mutableStateOf("")
    }
    var personsTravelling1 = remember { mutableStateOf("") }
    var moreTrips1 = remember { mutableStateOf("") }

    var showloadingsign by remember { mutableStateOf(false) }
    var context = LocalContext.current
    fun senddata(task : String)
    {

        if(origin1.value.isNotEmpty() ||
            destination1.value.isNotEmpty() ||
            tripPurpose1.value.isNotEmpty() ||
            tripChainMade1.value.isNotEmpty() ||
            modeOfTravel1.value.isNotEmpty() ||
            householdVehicleUsed1.value.isNotEmpty() ||
            departureTime1.value.isNotEmpty() ||

            arrivalTime1.value.isNotEmpty() ||
            travelCost1.value.isNotEmpty() ||
            duration1.value.isNotEmpty() ||
            personsTravelling1.value.isNotEmpty() ||
            moreTrips1.value.isNotEmpty() ||

                costfeedback.isNotEmpty() ||
                outcomefeedback.isNotEmpty() ||
                timefeedback.isNotEmpty() ||
                travelfeedback.isNotEmpty()){
            val data = origin_destination(
                device_id = getAndroidID(context)!!,
                day.value,
                origin1.value,
                destination1.value,
                mainTripPurpose1.value,
                tripChainMade1.value,
                modeOfTravel1.value,
                householdVehicleUsed1.value,
                departureTime1.value,
                arrivalTime1.value,
                travelCost1.value,
                duration1.value,

                personsTravelling1.value,
                moreTrips1.value,
                tripno.value,
                overallfeedback = travelfeedback,
                timefeedback  = timefeedback,
                costfeedback = costfeedback,
                outcomefeedback  = outcomefeedback,

                channelTripPurpose = tripPurpose1.value,
                walked = walked.value,
                walkedtime = walkedtime.value
            )
            CoroutineScope(Dispatchers.Main).launch{
                delay(3000)
                showloadingsign = true
                FirebaseFirestore.getInstance().collection("UserTripInfo")
                    .add(data)
                    .addOnSuccessListener {
                        showloadingsign = false
                        Toast.makeText(context,"Successful",Toast.LENGTH_SHORT).show()
                        if(task == "exit"){ mynavHostController.navigate(screens.thankyou.route){
                            launchSingleTop = true
                            popUpTo(screens.dayform.route)
                            {
                                inclusive = true
                            }
                        } }
                        else { mynavHostController.navigate(screens.dayform.route){
                            launchSingleTop = true
                            popUpTo(screens.dayform.route)
                            {
                                inclusive = true

                            }
                        }}

                    }
                    .addOnFailureListener {exception ->
                        Toast.makeText(context,exception.message,Toast.LENGTH_SHORT).show()
                        showloadingsign = false
                    }
            }

        }
        else
        {
            Toast.makeText(context,"Field Empty",Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "Trip information",
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
        )
        RadioGroup(options = listOf("1","2","3","4","5","6","7","8","9","10"), selectedOption = day , onOptionSelected = { /*TODO*/ }, label = "Day")
        RadioGroup(options = listOf("Trip_1","Trip_2"), selectedOption = tripno , onOptionSelected = { /*TODO*/ }, label = "Trip No.")


        OutlinedTextField(
            value = origin1.value,
            singleLine = true,
            onValueChange = { origin1.value = it },
            label = { Text("Origin of trip") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue, textColor = Color.Black, backgroundColor = Color.White)
        )

        OutlinedTextField(
            value = destination1.value,
            singleLine = true,
            onValueChange = { destination1.value = it },
            label = { Text("Destination of trip") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, backgroundColor = Color.White, unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )


        RadioGroup(
            options = listOf(
                "Work (Office/Business)",
                "Education",
                "Shopping",
                "Recreation",
                "Others"
            ),
            selectedOption = mainTripPurpose1,
            label = "Purpose of Trip",
            onOptionSelected = {}


     )
        RadioGroup(
            options = listOf("Yes", "No"),
            selectedOption = tripChainMade1,
                    label = "Was the Trip chain Made by you in this Trip",
            onOptionSelected = {}
        )

        if (tripChainMade1.value == "Yes") {
            OutlinedTextField(
                value = tripPurpose1.value,
                singleLine = true,
                onValueChange = { tripPurpose1.value = it },
                label = { Text("Purpose of making the trip chain") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp)
                , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, backgroundColor = Color.White, unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
            )
        }

//        Text("Mode of Travel")
        RadioGroup(
            options = listOf(
                "Bus",
                "Mini-Bus",
                "Two-wheeler",
                "Auto",
                "Taxi",
                "Car",
                "Walking",
                "Bicycle"
            ),
            selectedOption = modeOfTravel1,
            label = "Mode of Travel for the trip",
            onOptionSelected = {}
        )


        RadioGroup(
            options = listOf("Yes", "No"),
            selectedOption = householdVehicleUsed1,
            label = "Household Vehicle used",
            onOptionSelected = {}
        )



        OutlinedTextField(
            value = departureTime1.value,
            singleLine = true,
            onValueChange = { departureTime1.value = it },
            readOnly = true,
            trailingIcon = { Icon(painter = painterResource(id = R.drawable.clockicon), tint = Color.Blue, contentDescription = "" , modifier = Modifier.clickable {
                showTimeChooser(context) { selectedhour, selectedminute ->
                    departureTime1.value = "$selectedhour:$selectedminute"
                }
            })},
            label = { Text("Departure Time") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
                .onFocusChanged {
                    if (it.isFocused) {
                        showTimeChooser(context) { selectedhour, selectedminute ->
                            departureTime1.value = "$selectedhour:$selectedminute"
                        }
                    }
                }
            , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, backgroundColor = Color.White, unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )

        OutlinedTextField(
            value = arrivalTime1.value,
            singleLine = true,
            onValueChange = { arrivalTime1.value = it },
            readOnly = true,
            trailingIcon = { Icon(painter = painterResource(id = R.drawable.clockicon), tint = Color.Blue, contentDescription = "" , modifier = Modifier.clickable {
                showTimeChooser(context) { selectedhour, selectedminute ->
                    arrivalTime1.value = "$selectedhour:$selectedminute"
                }
            })},
            label = { Text("Arrival Time") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
                .onFocusChanged {
                    if (it.isFocused) {
                        showTimeChooser(context) { selectedhour, selectedminute ->
                            arrivalTime1.value = "$selectedhour:$selectedminute"
                        }
                    }
                }
            , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, backgroundColor = Color.White, unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )

        OutlinedTextField(
            value = travelCost1.value,
            singleLine = true,
            onValueChange = { travelCost1.value = it },
            label = { Text("Travel Cost (In Rs)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, backgroundColor = Color.White, unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )


        OutlinedTextField(
            value = duration1.value,
            singleLine = true,
            onValueChange = { duration1.value = it },
            label = { Text("Duration (in Minutes)") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, backgroundColor = Color.White, unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )


        RadioGroup(
            options = listOf("yes","no"),
            selectedOption = walked,
            label = "Did you Walk during the Trip?",
            onOptionSelected = {}
        )
        if (walked.value == "yes")
        {
            OutlinedTextField(
                value = walkedtime.value,
                singleLine = true,
                onValueChange = { walkedtime.value = it },
                label = { Text("Walked Time (in Minutes)") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp)
                , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, backgroundColor = Color.White, unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
            )
        }

        OutlinedTextField(
            value = personsTravelling1.value,
            singleLine = true,
            onValueChange = { personsTravelling1.value = it },
            label = { Text("No. of Persons Travelling with you (0 if alone)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, backgroundColor = Color.White, unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )

//        Text("Did You make any more trips")


        Spacer(modifier = Modifier.width(20.dp))

        KindRadioGroupUsage(question = "How satisfied were you with your overall travel experience?" , travelfeedback,travelsetSelected)
        Spacer(modifier = Modifier.width(20.dp))
        KindRadioGroupUsage(question = "How satisfied were you with the duration of your travel (Travel Time)?" , timefeedback,timesetSelected)
        Spacer(modifier = Modifier.width(20.dp))
        KindRadioGroupUsage(question = "How satisfied were you with the expenses of your trip (Travel Cost)?" , costfeedback,costsetSelected)
        Spacer(modifier = Modifier.width(20.dp))
        KindRadioGroupUsage(question = "How satisfied were you with the success of your trip for its intended purpose (Trip Outcome)?" , outcomefeedback,outcomesetSelected)
        Spacer(modifier = Modifier.width(20.dp))
        RadioGroup(
            options = listOf("Yes", "No"),
            selectedOption = moreTrips1,
            label = "Did You make any more trips",
            onOptionSelected = {}
        )


        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.background(
            Color.White)){
            OutlinedButton(
                onClick = {

                    senddata("exit")


                },
                modifier = Modifier

                    .padding(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Blue
                ),
                border = BorderStroke(
                    2.dp,
                    Color.Blue
                )
            ) {
                Text(text = "Submit")
            }

            if (moreTrips1.value == "Yes") {
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(onClick = {

                    senddata("more")



                }, modifier = Modifier

                    .padding(10.dp), colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White, contentColor = Color.Blue), border = BorderStroke(2.dp,
                    Color.Blue)
                ) {
                    Text(text = "Next Trip Details")
                }
            }


        }


    }
    if(showloadingsign){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent), contentAlignment = Alignment.Center
        )
        {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp),
                color = Color.Blue
            )
        }
    }

}



@Composable
fun RadioGroup(
    options: List<String>,
    selectedOption: MutableState<String>,
    onOptionSelected: () -> Unit,
    label : String
){
    var showdropdown by remember {
        mutableStateOf(false
        )
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally , ) {
        OutlinedTextField(
            value = selectedOption.value,
            onValueChange = { selectedOption.value = it },
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
                .clickable { showdropdown = !showdropdown }
                .onFocusChanged { it ->
                    showdropdown = it.isFocused
                }
            ,
            trailingIcon = { Icon(imageVector = if(!showdropdown) {
                Icons.Filled.ArrowDropDown
            }else
            {Icons.Filled.KeyboardArrowUp}, tint = Color.Blue,contentDescription = "", modifier = Modifier.clickable{showdropdown = !showdropdown})}
            , colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.Black, backgroundColor = Color.White, unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )
        DropdownMenu(modifier = Modifier
            .background(Color.White)
            .height(300.dp)
            .clickable { }, expanded = showdropdown, onDismissRequest = { showdropdown = false }) {
            for (i in options)
            {
                DropdownMenuItem(onClick = {
                    selectedOption.value = i
                    showdropdown = false
                                           }, modifier = Modifier.background(
                    Color.White)) {
                    Text(text = i, color = Color.Black)
                }
            }

        }
    }
}


data class origin_destination(
    val device_id: String  ,
    val day : String ,
    val origin : String
    , val destination : String
    , val purpose : String
    , val Trip_Chain_Made : String
    , val Mode_of_Travel : String
    , val Household_vehicle_used : String
    , val departureTime : String
    , val arrivalTime : String
    , val travelCost : String
    , val duration : String

    , val no_of_friends : String
    , val more_Trips : String
    , val trip_no : String
    , val overallfeedback : String
    , val timefeedback : String,
     val costfeedback : String
    , val outcomefeedback : String
  ,
val channelTripPurpose : String
,val walked : String
,val walkedtime : String)




fun showTimeChooser(context : Context , onTimeSelected : (hour : String , minute : String) -> Unit){

    val calender = Calendar.getInstance()
    val currenthour = calender.get(Calendar.HOUR_OF_DAY)
    val currentminute = calender.get(Calendar.MINUTE)

    val timePickerDialog = TimePickerDialog(
        context , {
            _:TimePicker , hour : Int , minute : Int ->
            onTimeSelected(String.format("%02d",hour) , String.format("%02d",minute))
        },
        currenthour,
        currentminute,
        true
    )

    timePickerDialog.show()

}
