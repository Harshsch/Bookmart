package com.example.bookmart


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookmart.AllScreens.HomeScreen.OrderPath.BookDisplay
import com.example.bookmart.AllScreens.HomeScreen.OrderPath.BuyNowPage
import com.example.bookmart.AllScreens.HomeScreen.HomeScreen
import com.example.bookmart.AllScreens.HomeScreen.MyOrdersScreen.MyOrdersScreen
import com.example.bookmart.AllScreens.HomeScreen.NoInternetScreen
import com.example.bookmart.AllScreens.HomeScreen.OrderPath.AddressCard
import com.example.bookmart.AllScreens.HomeScreen.OrderPath.OrderPlacedPage
import com.example.bookmart.AllScreens.HomeScreen.OrderPath.Order_Details
import com.example.bookmart.AllScreens.HomeScreen.OrderPath.Payment
import com.example.bookmart.AllScreens.HomeScreen.SettingScreen.FAQ
import com.example.bookmart.AllScreens.HomeScreen.SettingScreen.SavedAddressesScreen
import com.example.bookmart.AllScreens.HomeScreen.SettingScreen.SettingsScreen
import com.example.bookmart.AllScreens.HomeScreen.SettingScreen.TermsAndCondition
import com.example.bookmart.AllScreens.HomeScreen.SplashScreen
import com.example.bookmart.AllScreens.HomeScreen.UserProfile.UserProfile
import com.example.bookmart.AllScreens.HomeScreen.isInternetConnected
import com.example.bookmart.data.itemList

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen",
    ) {
        composable("splash_screen") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
            SplashScreen(navController = navController)
        }}


        composable("no_internet") {
            NoInternetScreen(navController = navController)
        }
        composable("home_route") {
            // Pass the selectedItemIndex to BottomBarScreen
            BottomBarScreen(navController) {
                if(!isInternetConnected(LocalContext.current))
                {
                    navController.navigate("no_internet")
                }
                else{
                HomeScreen(navController = navController)

            }}
        }
        composable("my_orders_route") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
            // Pass the selectedItemIndex to BottomBarScreen
            BottomBarScreen(navController) {
                MyOrdersScreen(navController = navController)
            }
            }
        }
        composable("settings_route") {
            // Pass the selectedItemIndex to BottomBarScreen
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else {
                BottomBarScreen(navController) {

                    SettingsScreen(navController = navController)
                }
            }
        }
        composable("Signup") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else {
                SignUpScreen(navController = navController)
            }
        }
        composable("Login") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else {
                LoginScreen(navController = navController)
            }
        }
        composable("Terms_and_Conditions") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else {
                TermsAndConditionsScreen()
            }
        }
        composable("User_Profile") {
            BottomBarScreen(navController) {
                if(!isInternetConnected(LocalContext.current))
                {
                    navController.navigate("no_internet")
                }
                else {
                    UserProfile(navController = navController)
                }
            }
        }
        composable("Terms") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
            BottomBarScreen(navController) {
                TermsAndCondition()
            }}
        }
        composable("SavedAddress") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
            BottomBarScreen(navController) {
                SavedAddressesScreen(navController = navController)
            }}
        }
        composable("FAQ") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
            BottomBarScreen(navController) {
                FAQ()
            }}
        }

        composable("confirmation_screen/{itemId}") {
                backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val itemId = arguments.getString("itemId")?.toIntOrNull()
            val selectedItem = itemList.find { it.id == itemId }
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
            selectedItem?.let { item ->
                OrderPlacedPage(navController = navController, item = item)

            }}
        }
//        composable("BuyNow/{itemId}") {
//                backStackEntry ->
//            val arguments = requireNotNull(backStackEntry.arguments)
//            val itemId = arguments.getString("itemId")?.toIntOrNull()
//            val selectedItem = itemList.find { it.id == itemId }
//            if(!isInternetConnected(LocalContext.current))
//            {
//                navController.navigate("no_internet")
//            }
//            else{
//            selectedItem?.let { item ->
//                BuyNowPage(navController = navController, item = item)
//
//            }}
//        }
        composable("BuyNow/{itemId}/{quantity}") {
                backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val quantity= arguments.getString("quantity")?.toInt()

            val itemId = arguments.getString("itemId")?.toIntOrNull()
            val selectedItem = itemList.find { it.id == itemId }
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
                selectedItem?.let { item ->
                    if (quantity != null) {
                        AddressCard(navController = navController, item = item,quantity=quantity)
                    }

                }}
        }
        composable("order_details/{itemId}/{quantity}") {
                backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val quantity= arguments.getString("quantity")?.toInt()

            val itemId = arguments.getString("itemId")?.toIntOrNull()
            val selectedItem = itemList.find { it.id == itemId }
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
                selectedItem?.let { item ->
                    if (quantity != null) {
                        Order_Details(navController = navController, item = item,quantity=quantity)
                    }

                }}
        }
        composable("Payment/{itemId}/{quantity}") {
                backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val itemId = arguments.getString("itemId")?.toIntOrNull()
            val quantity= arguments.getString("quantity")?.toInt()
            val selectedItem = itemList.find { it.id == itemId }
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
                selectedItem?.let { item ->
                    if (quantity != null) {
                        Payment(navController = navController, item = item, quantity =quantity )
                    }

                }}
        }

        composable("BookDisplay/{itemId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val itemId = arguments.getString("itemId")?.toIntOrNull()
            val selectedItem = itemList.find { it.id == itemId }
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
            selectedItem?.let { item ->
                BottomBarScreen(navController) {
                    BookDisplay(navController = navController, item = item)
                }}
            }
        }


    }

}