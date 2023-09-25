package com.example.bookmart.SettingScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
@Composable
fun SettingsScreen(navController: NavController) {
    // Step 1: Create mutable state variables for settings options
    var isNotificationEnabled by remember { mutableStateOf(true) }
    var isDarkModeEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Step 2: Display the screen title
        Text(
            text = "Settings",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Step 3: Add the first settings option (Receive Notifications)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Receive Notifications")
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(
                checked = isNotificationEnabled,
                onCheckedChange = { isChecked ->
                    isNotificationEnabled = isChecked
                    // Step 4: Implement your notification settings logic here
                    // For example, interact with your notification system here.
                }
            )
        }

        // Step 5: Add the second settings option (Dark Mode)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "Dark Mode")
            Spacer(modifier = Modifier.width(16.dp))
            Switch(
                checked = isDarkModeEnabled,
                onCheckedChange = { isChecked ->
                    isDarkModeEnabled = isChecked
                    // Step 6: Implement your dark mode settings logic here
                    // For example, update your app's theme based on the dark mode status.
                }
            )
        }

        // Step 7: Add more settings options or content as needed.

        // Step 8: Add a button to navigate back to the home screen or other actions.
        Button(
            onClick = { navController.navigate("home_route") }
        ) {
            Text(text = "Back to Home")
        }
    }
}
