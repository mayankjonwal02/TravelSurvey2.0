package com.jonwal.travel_survey_4.screens

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.jonwal.travel_survey_4.navigation.screens
import getAndroidID

@Composable
fun dayOneForm(mynavHostController: NavHostController) {
    var day = remember {
        mutableStateOf("")
    }
    var origin1 = remember { mutableStateOf("") }
    var destination1 = remember { mutableStateOf("") }
    var mainTripPurpose1 = remember { mutableStateOf("") }
    var tripChainMade1 =  remember { mutableStateOf("") }
    var tripPurpose1 = remember { mutableStateOf("") }
    var modeOfTravel1 = remember { mutableStateOf("") }
    var workinghrs = remember { mutableStateOf("") }
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
    fun senddata()
    {

        if(origin1.value.isNotEmpty() ||
            destination1.value.isNotEmpty() ||
            tripPurpose1.value.isNotEmpty() ||
            tripChainMade1.value.isNotEmpty() ||
            modeOfTravel1.value.isNotEmpty() ||
            householdVehicleUsed1.value.isNotEmpty() ||
            departureTime1.value.isNotEmpty() ||
            workinghrs.value.isNotEmpty() ||
            arrivalTime1.value.isNotEmpty() ||
            travelCost1.value.isNotEmpty() ||
            duration1.value.isNotEmpty() ||
            personsTravelling1.value.isNotEmpty() ||
            moreTrips1.value.isNotEmpty() ){
            val data = origin_destination(
                device_id = getAndroidID(context)!!,
                day.value,
                origin1.value,
                destination1.value,
                tripPurpose1.value,
                tripChainMade1.value,
                modeOfTravel1.value,
                householdVehicleUsed1.value,
                departureTime1.value,
                arrivalTime1.value,
                travelCost1.value,
                duration1.value,
                workinghrs.value,
                personsTravelling1.value,
                moreTrips1.value,
                tripno.value
            )
            FirebaseFirestore.getInstance().collection("UserTripInfo")
                .add(data)
                .addOnSuccessListener {
                    showloadingsign = false
                    Toast.makeText(context,"Successful",Toast.LENGTH_SHORT).show()
                mynavHostController.navigate(screens.thankyou.route)}
                .addOnFailureListener {Toast.makeText(context,"Network Error",Toast.LENGTH_SHORT).show()
                showloadingsign = false
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
            text = "Trip Form",
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
        )
        RadioGroup(options = listOf("1","2","3","4","5","6","7","8","9","10"), selectedOption = day , onOptionSelected = { /*TODO*/ }, label = "Day")
        RadioGroup(options = listOf("Trip_1","Trip_2"), selectedOption = tripno , onOptionSelected = { /*TODO*/ }, label = "Trip No.")


        OutlinedTextField(
            value = origin1.value,
            onValueChange = { origin1.value = it },
            label = { Text("Origin of trip") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )

        OutlinedTextField(
            value = destination1.value,
            onValueChange = { destination1.value = it },
            label = { Text("Destination of trip") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
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
            label = "Purpose",
            onOptionSelected = {}


     )
        RadioGroup(
            options = listOf("Yes", "No"),
            selectedOption = tripChainMade1,
                    label = "Trip Chain made",
            onOptionSelected = {}
        )

        if (tripChainMade1.value == "Yes") {
            OutlinedTextField(
                value = tripPurpose1.value,
                onValueChange = { tripPurpose1.value = it },
                label = { Text("Purpose") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(8.dp)
                , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
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
            label = "Mode of Travel",
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
            onValueChange = { departureTime1.value = it },
            label = { Text("Departure Time") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )

        OutlinedTextField(
            value = arrivalTime1.value,
            onValueChange = { arrivalTime1.value = it },
            label = { Text("Arrival Time") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )

        OutlinedTextField(
            value = travelCost1.value,
            onValueChange = { travelCost1.value = it },
            label = { Text("Travel Cost") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )

        OutlinedTextField(
            value = duration1.value,
            onValueChange = { duration1.value = it },
            label = { Text("Duration") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )
        OutlinedTextField(
            value = workinghrs.value,
            onValueChange = { workinghrs.value = it },
            label = { Text("Duration") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )

        OutlinedTextField(
            value = personsTravelling1.value,
            onValueChange = { personsTravelling1.value = it },
            label = { Text("No. of Persons Travelling with you (0 if alone)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
        )

//        Text("Did You make any more trips")
        RadioGroup(
            options = listOf("Yes", "No"),
            selectedOption = moreTrips1,
            label = "Did You make any more trips",
            onOptionSelected = {}
        )

        if (moreTrips1.value == "Yes") {
            Spacer(modifier = Modifier.height(16.dp))


        }
        Spacer(modifier = Modifier.width(20.dp))
        OutlinedButton(onClick = {

            showloadingsign = true
                senddata()

        }, modifier = Modifier

            .padding(10.dp), colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White, contentColor = Color.Blue), border = BorderStroke(2.dp,
            Color.Blue)
        ) {
            Text(text = "Submit")
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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = selectedOption.value,
            onValueChange = { selectedOption.value = it },
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
                .onFocusChanged { it ->
                    showdropdown = it.isFocused
                }
            , colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Color.Gray, unfocusedLabelColor = Color.Gray, focusedLabelColor = Color.Blue, focusedBorderColor = Color.Blue)
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
    , val workinghrs : String
    , val no_of_friends : String
    , val more_Trips : String
    , val trip_no : String )
