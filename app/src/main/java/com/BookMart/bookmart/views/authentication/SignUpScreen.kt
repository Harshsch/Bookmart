package com.BookMart.bookmart

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun SignUpScreen(navController: NavController,signupViewModel: SignupViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    LaunchedEffect(scrollState) {
        // Animate scroll to the top
        scrollState.scrollTo( 10000)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()
                .padding(16.dp)
                .verticalScroll(state = scrollState),
                ) {

                NormalTextComponent(value = stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id =R.string.first_name),
                    painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = com.BookMart.bookmart.R.string.last_name),
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.baseline_email_24),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                   errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.lock),
                    onTextSelected = {
                     signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                  },
                   errorStatus = signupViewModel.registrationUIState.value.passwordError
                )

                CheckboxComponent(value = stringResource(id =R.string.terms_and_conditions),
                    onTextSelected = {
                        navController.navigate("Terms_and_Conditions")
                 },
                    onCheckedChange = {
                       signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
               )

                Spacer(modifier = Modifier.height(40.dp))
                val context = LocalContext.current


                ButtonComponent(
                    value = stringResource(id = com.BookMart.bookmart.R.string.register),
                    onButtonClicked = {
                        val email = signupViewModel.registrationUIState.value.email
                        val password = signupViewModel.registrationUIState.value.password
                        // Assuming you have the user's first name and last name
                        val firstName = signupViewModel.registrationUIState.value.firstName
                        val lastName = signupViewModel.registrationUIState.value.lastName
                        val fullName = "$firstName $lastName"

                        Firebase.auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val user = Firebase.auth.currentUser

                                    // Set the display name
                                    val userProfileChangeRequest = UserProfileChangeRequest.Builder()
                                        .setDisplayName(fullName)
                                        .build()

                                    user?.updateProfile(userProfileChangeRequest)
                                        ?.addOnCompleteListener { updateTask ->
                                            if (updateTask.isSuccessful) {
                                                // Display name updated successfully
                                                navController.navigate("home_route")
                                            } else {
                                                // Handle the error
                                                val exception = updateTask.exception
                                                // Handle the exception
                                            }
                                        }
                                    user?.sendEmailVerification()
                                        ?.addOnSuccessListener {
                                            Toast.makeText(context, "Please Verify Email", Toast.LENGTH_SHORT).show()

                                        }
                                    navController.navigate("home_route")
                                    {
                                        popUpTo("Signup") { inclusive = true }
                                    }
                                } else {
                                    // Handle the registration error
                                    val exception = task.exception
                                    // Handle the exception
                                    Toast.makeText(context, "Login! User Already exists ", Toast.LENGTH_SHORT).show()

                                }
                            }

                    },
                    isEnabled = signupViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    navController.navigate("Login")
                    {
                        popUpTo("Signup") { inclusive = true }
                    }
                })

            }

        }

        if(signupViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
   }




}

