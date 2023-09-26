package com.nativemobilebits.loginflow.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bookmart.ButtonComponent
import com.example.bookmart.CheckboxComponent
import com.example.bookmart.ClickableLoginTextComponent
import com.example.bookmart.DividerTextComponent
import com.example.bookmart.HeadingTextComponent
import com.example.bookmart.MyTextFieldComponent
import com.example.bookmart.NormalTextComponent
import com.example.bookmart.PasswordTextFieldComponent
import com.nativemobilebits.loginflow.data.signup.SignupUIEvent
import com.nativemobilebits.loginflow.data.signup.SignupViewModel


@Composable
fun SignUpScreen(navController: NavController,signupViewModel: SignupViewModel = viewModel()) {

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
            Column(modifier = Modifier.fillMaxSize()) {

                NormalTextComponent(value = stringResource(id = com.example.bookmart.R.string.hello))
                HeadingTextComponent(value = stringResource(id = com.example.bookmart.R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = com.example.bookmart.R.string.first_name),
                    painterResource(id = com.example.bookmart.R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = com.example.bookmart.R.string.last_name),
                    painterResource = painterResource(id = com.example.bookmart.R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = com.example.bookmart.R.string.email),
                    painterResource = painterResource(id = com.example.bookmart.R.drawable.message),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = com.example.bookmart.R.string.password),
                    painterResource = painterResource(id = com.example.bookmart.R.drawable.ic_lock),
//                    onTextSelected = {
//                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
//                    },
//                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )

                CheckboxComponent(value = stringResource(id = com.example.bookmart.R.string.terms_and_conditions),
                    onTextSelected = {
                        navController.navigate("Terms_and_Conditions")
                 },
//                    onCheckedChange = {
//                        signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
//                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = com.example.bookmart.R.string.register),
                    onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
//                    isEnabled = signupViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    navController.navigate("Login")
                })
            }

        }

//        if(signupViewModel.signUpInProgress.value) {
//            CircularProgressIndicator()
//        }
   }


}

