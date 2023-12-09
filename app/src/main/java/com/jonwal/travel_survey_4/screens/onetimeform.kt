import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.jonwal.travel_survey_4.navigation.screens
import com.jonwal.travel_survey_4.screens.RadioGroup
import com.jonwal.travel_survey_4.screens.getsharedpref

@Composable
fun onetimeform(mynavHostController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var homeAddress by remember { mutableStateOf("") }
    var workAddress by remember { mutableStateOf("") }
    var selectedGender = remember { mutableStateOf("") }
    var education = remember { mutableStateOf("") }
    var income = remember { mutableStateOf("") }

    var context = LocalContext.current

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.O)
    fun senddata() {
        if (name.isEmpty() || age.isEmpty() || education.value.isEmpty() ||
            income.value.isEmpty() || email.isEmpty() || phone.isEmpty() || homeAddress.isEmpty() || workAddress.isEmpty() || selectedGender.value.isEmpty()) {
            Toast.makeText(context, "Field Empty", Toast.LENGTH_SHORT).show()
            return
        }

        if(phone.length == 10){
            val deviceId = getAndroidID(context) ?: "UNKNOWN"

            val data = MyUserData(
                deviceId = deviceId,
                name = name,
                age = age,
                email = email,
                phone = phone,
                education = education.value,
                income = income.value,
                homeAddress = homeAddress,
                workAddress = workAddress,
                selectedGender = selectedGender.value
            )

            FirebaseFirestore.getInstance().collection("UserFormData")
                .add(data)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()
                    getsharedpref(context).edit().putBoolean("formfilled", true).apply()
                    mynavHostController.navigate(screens.startservice.route)
                }
                .addOnFailureListener {
                    exception ->
                    Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
                }
        }
        else {
            Toast.makeText(context,"Phone Number should be of lenght 10",Toast.LENGTH_SHORT).show()
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
            textAlign = TextAlign.Center,
            text = "User-Data",
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Your Name") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Blue,
                focusedBorderColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        var dropdown by remember {
            mutableStateOf(false)
        }


        RadioGroup(
            options = listOf("Male","Female","Non-Binary"),
            selectedOption = selectedGender,
            label = "Gender",
            onOptionSelected = {}
        )
//        Column() {
//            OutlinedTextField(
//                value = selectedGender,
//                onValueChange = { selectedGender = it },
//                label = { Text("Gender") },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.White)
//                    .padding(8.dp)
//                    .onFocusChanged { it ->
//                        if (it.isFocused) {
//                            dropdown = true
//                        } else if (!it.isFocused) {
//                            dropdown = false
//                        }
//                    },
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    unfocusedBorderColor = Color.Gray,
//                    unfocusedLabelColor = Color.Gray,
//                    focusedLabelColor = Color.Blue,
//                    focusedBorderColor = Color.Blue
//                )
//            )
//            if (dropdown) {
//                DropdownMenu(
//                    expanded = dropdown,
//                    onDismissRequest = { dropdown = false },
//                    modifier = Modifier.background(
//                        Color.White
//                    )
//                ) {
//                    for (i in listOf("Male", "Female", "Non-Binary")) {
//                        DropdownMenuItem(
//                            modifier = Modifier.background(Color.White),
//                            onClick = {
//                                selectedGender = i
//                                dropdown = false
//                            }) {
//                            Text(text = i, color = Color.Black)
//                        }
//                    }
//                }
//            }
//        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Blue,
                focusedBorderColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email address") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Blue,
                focusedBorderColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phone,

            onValueChange = { if(it.length <= 10){ phone = it }else{Toast.makeText(context,"Length should be 10",Toast.LENGTH_SHORT).show()} },
            label = { Text("Phone no.") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Blue,
                focusedBorderColor = Color.Blue
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = homeAddress,
            onValueChange = { homeAddress = it },
            label = { Text("Home Address") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color                .Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Blue,
                focusedBorderColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        RadioGroup(
            options = listOf("upto 10th", "12th" , "Graduation","Masters and Above"),
            selectedOption = education,
            label = "Education",
            onOptionSelected = {}
        )

        RadioGroup(
            options = listOf("< 10000", "10000 to 20000" , "20000 to 35000","35000 to 50000","> 50000"),
            selectedOption = income,
            label = "Average monthly income ( in Indian rupees)",
            onOptionSelected = {}
        )

        OutlinedTextField(
            value = workAddress,
            onValueChange = { workAddress = it },
            label = { Text("Work Address") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Blue,
                focusedBorderColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = "*Please note that your information will remain confidential and not be shared with anyone.",
            style = MaterialTheme.typography.body2,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(20.dp))

        OutlinedButton(
            onClick = {
                senddata()
                      },
            modifier = Modifier.padding(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.Blue
            ),
            border = BorderStroke(2.dp, Color.Blue)
        ) {
            Text(text = "Submit")
        }
    }
}

data class MyUserData(
    var deviceId: String,
    val name: String,
    val age: String,
    val email: String,
    val phone: String,
    val homeAddress: String,
    val workAddress: String,
    val selectedGender: String,
    val education: String,
    val income: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun getAndroidID(context: Context): String? {
    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}

