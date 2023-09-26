package com.example.bookmart.AllScreens.HomeScreen.MyOrdersScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
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
fun MyOrdersScreen(navController: NavController) {
    var isCartEmpty by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Orders",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // You can add more content related to "My Orders" here, such as a list of orders.
        // For example:
        OrderItem(orderNumber = "Order #12345", orderDate = "Date: 2023-09-18")
        OrderItem(orderNumber = "Order #54321", orderDate = "Date: 2023-09-17")

        // Add more order items or other content as needed.

        Text(
            text = "Cart",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(top = 16.dp)
        )

        // Display cart contents or items here.
        // For example:
        CartItem(productName = "Product A", price = "$10")
        CartItem(productName = "Product B", price = "$15")

        // Add more cart items or other cart-related content as needed.


            Button(
                onClick = { /* Implement your checkout logic here */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp)
            ) {
                Text(text = "Checkout")
            }

        // Button to navigate back to the home screen or other actions
        Button(
            onClick = { navController.navigate("home_route") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Back to Home")
        }
    }
}

@Composable
fun CartItem(productName: String, price: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = productName)
        Text(text = price)
    }
}

@Composable
fun OrderItem(orderNumber: String, orderDate: String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = orderNumber, fontWeight = FontWeight.Bold)
            Text(text = orderDate)
        }
    }
}


