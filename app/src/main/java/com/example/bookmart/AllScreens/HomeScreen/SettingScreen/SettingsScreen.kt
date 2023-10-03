package com.example.bookmart.AllScreens.HomeScreen.SettingScreen
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookmart.R
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SettingsScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            item {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Hey! BookMart Customer",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.AccountCircle,
                    title = "Account Settings",
                    onClick = { /* Handle Account Settings click */ }
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Person,
                    title = "Edit Profile",
                    onClick = { /* Handle Edit Profile click */ }
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Place,
                    title = "Saved Addresses",
                    onClick = { /* Handle Saved Addresses click */ }
                )
            }

            item {
                Divider(
                    color = Color.Gray,
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }

            item {
                Text(
                    text = "My Activity",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Star,
                    title = "Reviews",
                    onClick = { /* Handle Reviews click */ }
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.QuestionAnswer,
                    title = "Questions & Answers",
                    onClick = { /* Handle Questions & Answers click */ }
                )
            }

            item {
                Divider(
                    color = Color.Gray,
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }

            item {
                Text(
                    text = "Feedback & Information",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Feedback,
                    title = "Feedback & Information",
                    onClick = { /* Handle Feedback & Information click */ }
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Policy,
                    title = "Terms, Policies and Licenses",
                    onClick = { /* Handle Terms click */ }
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.QuestionAnswer,
                    title = "Browse FAQs",
                    onClick = { /* Handle FAQs click */ }
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Button(
                    onClick = {  val firebaseAuth = FirebaseAuth.getInstance()

                        firebaseAuth.signOut()

                        val authStateListener = FirebaseAuth.AuthStateListener {
                            if (it.currentUser == null) {
                                Log.d(TAG, "inside signout success")

                            } else {
                                Log.d(TAG, "signoutnot complete")

                            }

                        }
                        firebaseAuth.addAuthStateListener(authStateListener)
                        navController.navigate("home_route")
                    }
                ) {
                    Text(text = "Logout")
                }
            }
        }

}

@Composable
fun SettingsItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.baseline_chevron_right_24),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
