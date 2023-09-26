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
import com.nativemobilebits.loginflow.data.signup.SignupUIEvent
import com.nativemobilebits.loginflow.data.signup.SignupViewModel

@Composable
fun UserProfile(navController: NavHostController,signupViewModel: SignupViewModel = viewModel()) {
    Surface(modifier = Modifier.fillMaxSize(),) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)) {
            Image(painter = painterResource(id = R.drawable.profile), contentDescription = "user", alignment = Alignment.Center)
            Spacer(modifier = Modifier.height(20.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.mobile_number),
                painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
            )

            MyTextFieldComponent(
                labelValue = "Email Id",
                painterResource = painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
//                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonComponent(
                value = stringResource(id = R.string.update ),
                    onButtonClicked = {
                        signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                    },
//                    isEnabled = signupViewModel.allValidationsPassed.value
            )

        }

    }


}
