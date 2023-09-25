package com.example.bookmart

sealed class Screen(val route: String, val title: String) {

    object Home : Screen("home", "Home")
    object MyOrders : Screen("my_orders", "MyOrders")
    object Settings : Screen("settings", "Settings")
}


