package com.BookMart.bookmart.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.BookMart.bookmart.BottomBarScreen
import com.BookMart.bookmart.HomeScreen
import com.BookMart.bookmart.LoginScreen
import com.BookMart.bookmart.SignUpScreen
import com.BookMart.bookmart.TermsAndConditionsScreen
import com.BookMart.bookmart.data.itemList
import com.BookMart.bookmart.topbackbar
import com.BookMart.bookmart.utils.helpers.isInternetConnected
import com.BookMart.bookmart.views.NoInternetScreen
import com.BookMart.bookmart.views.home.SplashScreen
import com.BookMart.bookmart.views.myorders.MyOrdersScreen
import com.BookMart.bookmart.views.orderflow.AddressCard
import com.BookMart.bookmart.views.orderflow.BookDisplay
import com.BookMart.bookmart.views.orderflow.OrderPlacedPage
import com.BookMart.bookmart.views.orderflow.Order_Details
import com.BookMart.bookmart.views.orderflow.Payment
import com.BookMart.bookmart.views.setting.FAQ
import com.BookMart.bookmart.views.setting.SavedAddressesScreen
import com.BookMart.bookmart.views.setting.SettingsScreen
import com.BookMart.bookmart.views.setting.TermsAndCondition
import com.BookMart.bookmart.views.setting.UserProfile

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
                topbackbar(navController)
                {
                    TermsAndConditionsScreen()
                }
            }
        }
        composable("User_Profile") {

                if(!isInternetConnected(LocalContext.current))
                {
                    navController.navigate("no_internet")
                }
                else {
                    topbackbar(navController)
                    {
                        val title="Profile"
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
                topbackbar(navController)
                {
                    TermsAndCondition()
                }
            }
        }
        composable("SavedAddress") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
                topbackbar(navController)
                {
                    SavedAddressesScreen(navController = navController)

                }
            }
        }
        composable("FAQ") {
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{
                topbackbar(navController)
                {
                FAQ()
            }
            }
        }

        composable("confirmation_screen/{itemId}/{orderkey}/{orderlistkey}") {
                backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val itemId = arguments.getString("itemId")?.toIntOrNull()
            val orderkey = arguments.getString("orderkey")
            val orderlistkey = arguments.getString("orderlistkey")
            val selectedItem = itemList.find { it.id == itemId }
            if(!isInternetConnected(LocalContext.current))
            {
                navController.navigate("no_internet")
            }
            else{

            selectedItem?.let { item ->
                if (orderkey != null) {
                    if (orderlistkey != null) {
                        OrderPlacedPage(navController = navController, item = item,orderkey=orderkey, orderlistkey =orderlistkey )
                    }
                }

            }}
        }

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
                topbackbar(navController)
                {
                selectedItem?.let { item ->
                    if (quantity != null) {
                        AddressCard(navController = navController, item = item,quantity=quantity)
                    }

                }}
            }
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
                topbackbar(navController)
                {
                selectedItem?.let { item ->
                    if (quantity != null) {
                        Order_Details(navController = navController, item = item,quantity=quantity)
                    }

                }}
            }
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
                topbackbar(navController)
                {
                selectedItem?.let { item ->
                    if (quantity != null) {
                        Payment(navController = navController, item = item, quantity =quantity )
                    }

                }}
            }
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
                topbackbar(navController)
                {
                    BookDisplay(navController = navController, item = item)
                }
            }
            }
        }


    }

}