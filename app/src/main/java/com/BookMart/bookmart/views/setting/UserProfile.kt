package com.BookMart.bookmart.views.setting


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.BookMart.bookmart.ButtonComponent
import com.BookMart.bookmart.HeadingTextComponent
import com.BookMart.bookmart.MyTextFieldComponent
import com.BookMart.bookmart.R
import com.BookMart.bookmart.SignupUIEvent
import com.BookMart.bookmart.SignupViewModel
import com.BookMart.bookmart.config.ui.theme.DarkSurfaceColor
import com.BookMart.bookmart.config.ui.theme.LightSurfaceColor
import com.BookMart.bookmart.domain.models.myorders.Address
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun UserProfile(
    navController: NavHostController,
    signupViewModel: SignupViewModel = viewModel()
) {
    val user = FirebaseAuth.getInstance().currentUser
    val userId = FirebaseAuth.getInstance().currentUser?.uid

  val lastValidFirstName = remember { mutableStateOf(signupViewModel.registrationUIState.value.firstName) }
    val lastValidLastName = remember { mutableStateOf(signupViewModel.registrationUIState.value.lastName) }
  var firstName by remember { mutableStateOf(signupViewModel.registrationUIState.value.firstName) }
    var lastName by remember { mutableStateOf(signupViewModel.registrationUIState.value.lastName) }

    var isUpdateButtonEnabled by remember { mutableStateOf(false) }
    var defaultAddress by remember { mutableStateOf<Address?>(null) }

    val defaultdatabaseReference =
        FirebaseDatabase.getInstance().getReference("users/$userId/defaultAddress")



    LaunchedEffect(Unit) {
         val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                  val value = snapshot.getValue(Address::class.java)
                if (value != null) {
                    defaultAddress = value
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error here
                println("Database Error: $error")
            }
        }
        defaultdatabaseReference.addValueEventListener(valueEventListener)
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = LightSurfaceColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                //.background(color = colorResource(id = R.color.DarkSurfaceColor))
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "User",
                alignment = Alignment.Center,
                modifier = Modifier.padding(16.dp)
                    .size(100.dp)
            )
            UserInfo(
                Name = user?.displayName,
                mobilenumber = defaultAddress?.mobileNumber,
                AddressStreet = defaultAddress?.streetAddress,
                AddressCity = defaultAddress?.city,
                navController=navController
            )

            // Display user profile image

            HeadingTextComponent(value = "Change Username")

            // Text fields for first name and last name
            MyTextFieldComponent(
                labelValue = "First Name",
                painterResource(id = R.drawable.profile),
                onTextChanged = {
                    firstName = it
                    signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    isUpdateButtonEnabled = isFormValid(firstName, lastName)
                },
                errorStatus = signupViewModel.registrationUIState.value.firstNameError
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.profile),
                onTextChanged = {
                    lastName = it
                    signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    isUpdateButtonEnabled = isFormValid(firstName, lastName)
                },
                errorStatus = signupViewModel.registrationUIState.value.lastNameError
            )

            Spacer(modifier = Modifier.height(20.dp))

            ButtonComponent(
                value = stringResource(id = R.string.update),
                onButtonClicked = {
                    updateUserProfile(firstName, lastName)
                    navController.navigate("home_route")
                },
                isEnabled = isUpdateButtonEnabled, // Enable/disable the button
            )
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            lastValidFirstName.value = firstName
            lastValidLastName.value = lastName
        }
    }

}
fun isFormValid(firstName: String, lastName: String): Boolean {
    return firstName.isNotBlank() && firstName.length >= 3 &&
            lastName.isNotBlank() && lastName.length >= 3
}
private fun updateUserProfile(firstName: String, lastName: String) {
    val user = FirebaseAuth.getInstance().currentUser
    val profileUpdates = UserProfileChangeRequest.Builder()
        .setDisplayName("$firstName $lastName")
        .build()

    user?.updateProfile(profileUpdates)
        ?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
        } else {
                val exception = task.exception
                if (exception != null) {

                }
            }
        }
}
@Composable
fun UserInfo(
   Name: String?,
    mobilenumber: String?,
   AddressStreet:String?,
   AddressCity:String?,
   navController:NavHostController
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkSurfaceColor,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "User Information ",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
            Row() {
                if (Name != null) {
                    Text(
                        text = "Name: $Name",
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                } else {
                    Text(
                        text = "Null",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

            }
            Row() {
                if (mobilenumber != null) {
                    Text(
                        text = "Mobile Number : $mobilenumber",
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                } else {
                    Text(
                        text = "Mobile Number not available",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (){


                if (mobilenumber != null) {
                    Text(
                        text = " Default Address : $AddressStreet\n $AddressCity",
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                } else {
                    Text(
                        text = "Mobile Number not available",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(90.dp))
                Icon(
                    painter = painterResource(id = R.drawable.baseline_edit_24) ,
                    contentDescription = "EditAddress",
                    modifier = Modifier.clickable(onClick = { navController.navigate("SavedAddress")}),
                    tint = Color.White
                )
            }
        }
    }
}

