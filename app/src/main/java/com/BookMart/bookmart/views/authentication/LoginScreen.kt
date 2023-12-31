package com.BookMart.bookmart

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.BookMart.bookmart.viewModels.authentication.login.LoginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
@Composable
fun LoginScreen(navController: NavController,loginViewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    LaunchedEffect(scrollState) {
        scrollState.scrollTo( 10000)
    }
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color=MaterialTheme.colorScheme.background

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(state = scrollState),
            ) {
                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_20)))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.baseline_email_24),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                   onTextSelected = {
                       loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_20)))

                if( loginViewModel.loginUIState.value.emailError)
                {ClickableForgotPasswordTextComponent(loginViewModel.loginUIState.value.email.toString()) }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_40)))


                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {

                        Firebase.auth.currentUser?.sendEmailVerification()
                            ?.addOnSuccessListener {
                                Toast.makeText(context, "Please Verify Email", Toast.LENGTH_SHORT).show()

                            }
                       loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)


                        navController.navigate("home_route")
                        {
                            popUpTo("Login") { inclusive = true }
                        }

                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_20)))

                DividerTextComponent()


                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navController.navigate("Signup")
                })
            }
        }

        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }

}

