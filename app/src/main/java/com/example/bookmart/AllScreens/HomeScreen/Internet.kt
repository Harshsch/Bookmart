package com.example.bookmart.AllScreens.HomeScreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable

fun NoInternetPopup(
    onDismiss: () -> Unit,
    modifier: Modifier,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("No Internet Connection") },
        text = {
            Text("Please check your internet connection and try again.")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        modifier = modifier
    )
}
