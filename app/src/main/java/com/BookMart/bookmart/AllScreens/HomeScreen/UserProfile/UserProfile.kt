package com.BookMart.bookmart.AllScreens.HomeScreen.UserProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.res.colorResource
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
import com.BookMart.bookmart.data.AddToCart.Address
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



    // Retrieve the last valid values of firstName and lastName
    val lastValidFirstName = remember { mutableStateOf(signupViewModel.registrationUIState.value.firstName) }
    val lastValidLastName = remember { mutableStateOf(signupViewModel.registrationUIState.value.lastName) }

    // Store the current values of firstName and lastName
    var firstName by remember { mutableStateOf(signupViewModel.registrationUIState.value.firstName) }
    var lastName by remember { mutableStateOf(signupViewModel.registrationUIState.value.lastName) }

    // Store the enabled/disabled state of the "Update" button
    var isUpdateButtonEnabled by remember { mutableStateOf(false) }
    var defaultAddress by remember { mutableStateOf<Address?>(null) }

    val defaultdatabaseReference =
        FirebaseDatabase.getInstance().getReference("users/$userId/defaultAddress")

    LaunchedEffect(Unit) {
        // Add a ValueEventListener to fetch and update the data
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Retrieve the Address object from Firebase
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
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.DarkPrimaryColor))
        ,
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(color = colorResource(id = R.color.DarkSurfaceColor))

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(10.dp)
                    .background(color = colorResource(id = R.color.DarkSurfaceColor))

            ) {
                UserInfo(
                    Name = user?.displayName,
                    mobilenumber = defaultAddress?.mobileNumber,
                    AddressStreet = defaultAddress?.streetAddress,
                    AddressCity=defaultAddress?.city
                )
                // Display user profile image
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "user",
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

                HeadingTextComponent(value = "Change Username  ")


                // Text fields for first name and last name
                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource(id = R.drawable.profile),
                    //value = firstName,
                    onTextChanged = {
                        firstName = it
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                        // Check validation and enable/disable the button
                        isUpdateButtonEnabled = isFormValid(firstName, lastName)
                    },
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource = painterResource(id = R.drawable.profile),
                    // value = lastName,
                    onTextChanged = {
                        lastName = it
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                        // Check validation and enable/disable the button
                        isUpdateButtonEnabled = isFormValid(firstName, lastName)
                    },
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Button to update user profile
                ButtonComponent(
                    value = stringResource(id = R.string.update),
                    onButtonClicked = {
                        // Update user data in Firebase
                        updateUserProfile(firstName, lastName)
                        navController.navigate("home_route")
                    },
                    isEnabled = isUpdateButtonEnabled, // Enable/disable the button
                )
            }
        }
    }

    // Update the last valid values when the input fields change
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

        // You can also set other profile information here, like photo URL, etc.
        .build()

    user?.updateProfile(profileUpdates)
        ?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Profile updated successfully
                // You can also update other user data in your Firebase Realtime Database or Firestore here.
            } else {
                // Handle error
                val exception = task.exception
                if (exception != null) {
                    // Handle the exception
                }
            }
        }
}
@Composable
fun UserInfo(
   Name: String?,
    mobilenumber: String?,
   AddressStreet:String?,
   AddressCity:String?
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.DarkPrimaryColor),
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "User Information ",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            if (Name != null) {
                Text(
                    text = "Name: $Name",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            } else {
                Text(
                    text = "Null",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            if (mobilenumber != null) {
                Text(
                    text = "Mobile Number : $mobilenumber",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            } else {
                Text(
                    text = "Mobile Number not available",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            if (mobilenumber != null) {
                Text(
                    text = " Default Address : $AddressStreet\n $AddressCity",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            } else {
                Text(
                    text = "Mobile Number not available",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
//            Button(
//                onClick = {
//                    navController.navigate("SavedAddress")
//
//
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp)
//            ) {
//                Text(text = "Change")
//            }
        }
    }
}

