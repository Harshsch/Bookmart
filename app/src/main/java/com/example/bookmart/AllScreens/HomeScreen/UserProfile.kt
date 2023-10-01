package com.example.bookmart.AllScreens.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.bookmart.ButtonComponent
import com.example.bookmart.MyTextFieldComponent
import com.example.bookmart.R
import com.example.bookmart.SignupUIEvent
import com.example.bookmart.SignupViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
@Composable
fun UserProfile(
    navController: NavHostController,
    signupViewModel: SignupViewModel = viewModel()
) {
    val user = FirebaseAuth.getInstance().currentUser

    // Retrieve the last valid values of firstName and lastName
    val lastValidFirstName = remember { mutableStateOf(signupViewModel.registrationUIState.value.firstName) }
    val lastValidLastName = remember { mutableStateOf(signupViewModel.registrationUIState.value.lastName) }

    // Store the current values of firstName and lastName
    var firstName by remember { mutableStateOf(signupViewModel.registrationUIState.value.firstName) }
    var lastName by remember { mutableStateOf(signupViewModel.registrationUIState.value.lastName) }

    // Store the enabled/disabled state of the "Update" button
    var isUpdateButtonEnabled by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            // Display user profile image
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "user",
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

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

    // Update the last valid values when the input fields change
    DisposableEffect(Unit) {
        onDispose {
            lastValidFirstName.value = firstName
            lastValidLastName.value = lastName
        }
    }
}

fun isFormValid(firstName: String, lastName: String): Boolean {
    // Add validation logic here
    return !firstName.isBlank() && !lastName.isBlank()
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
