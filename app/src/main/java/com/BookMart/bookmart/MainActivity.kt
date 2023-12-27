package com.BookMart.bookmart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.BookMart.bookmart.config.ui.theme.BookMartTheme
import com.BookMart.bookmart.navigation.Navigation
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookMartTheme {
                FirebaseApp.initializeApp(this)
                Navigation()
            }

        }
    }
}
























