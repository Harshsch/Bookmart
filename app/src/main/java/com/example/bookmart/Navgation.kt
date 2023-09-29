package com.example.bookmart


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookmart.AllScreens.HomeScreen.BookDisplay
import com.example.bookmart.AllScreens.HomeScreen.HomeScreen
import com.example.bookmart.AllScreens.HomeScreen.MyOrdersScreen.MyOrdersScreen
import com.example.bookmart.AllScreens.HomeScreen.SettingScreen.SettingsScreen
import com.example.bookmart.AllScreens.HomeScreen.SplashScreen
import com.example.bookmart.AllScreens.HomeScreen.UserProfile
import com.nativemobilebits.loginflow.screens.LoginScreen
import com.nativemobilebits.loginflow.screens.TermsAndConditionsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()





    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("home_route") {
            // Pass the selectedItemIndex to BottomBarScreen
            BottomBarScreen(navController) {
                HomeScreen(navController = navController)
            }
        }
        composable("my_orders_route") {
            // Pass the selectedItemIndex to BottomBarScreen
            BottomBarScreen(navController) {
                MyOrdersScreen(navController = navController)
            }
        }
        composable("settings_route") {
            // Pass the selectedItemIndex to BottomBarScreen
            BottomBarScreen(navController) {
                SettingsScreen(navController = navController)
            }
        }
        composable("Signup") {
            SignUpScreen(navController = navController)
        }
        composable("Login") {
            LoginScreen(navController = navController)
        }
        composable("Terms_and_Conditions") {
            TermsAndConditionsScreen(navController = navController)
        }
        composable("User_Profile") {
            UserProfile(navController = navController)
        }

        composable("BookDisplay/{itemId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val itemId = arguments.getString("itemId")?.toIntOrNull()
            val selectedItem = itemList.find { it.id == itemId }
            selectedItem?.let { item ->
                BottomBarScreen(navController) {
                    BookDisplay(navController = navController, item = item)
                }
            }
        }

    }

}