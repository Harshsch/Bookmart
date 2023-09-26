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
import com.example.bookmart.ClickableLoginTextComponent
import com.example.bookmart.DividerTextComponent
import com.example.bookmart.HeadingTextComponent
import com.example.bookmart.MyTextFieldComponent
import com.example.bookmart.NormalTextComponent
import com.example.bookmart.PasswordTextFieldComponent
import com.example.bookmart.R
import com.example.bookmart.UnderLinedTextComponent
import com.nativemobilebits.loginflow.data.login.LoginUIEvent
import com.nativemobilebits.loginflow.data.login.LoginViewModel
import com.nativemobilebits.loginflow.data.signup.SignupUIEvent
import com.nativemobilebits.loginflow.data.signup.SignupViewModel


@Composable
fun LoginScreen(navController: NavController,loginViewModel: LoginViewModel = viewModel()) {

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

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
//                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
//                    onTextSelected = {
//                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
//                    },
//                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                       loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
//                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navController.navigate("Signup")
                })
            }
        }

//        if(loginViewModel.loginInProgress.value) {
//            CircularProgressIndicator()
//        }
    }


//    SystemBackButtonHandler {
//        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
//    }
}

