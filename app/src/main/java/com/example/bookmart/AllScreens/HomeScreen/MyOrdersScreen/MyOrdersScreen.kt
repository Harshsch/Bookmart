package com.example.bookmart.AllScreens.HomeScreen.MyOrdersScreen




import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookmart.R
import com.example.bookmart.data.AddToCart.CartItem
import com.example.bookmart.data.AddToCart.MyOrders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


@Composable
fun MyOrdersScreen(navController: NavController) {
    val cartItems = remember { mutableStateListOf<CartItem>() }
    val ordersItems = remember { mutableStateListOf<MyOrders>() }
    val userId = FirebaseAuth.getInstance().currentUser?.uid // Get the user's ID

    val database = FirebaseDatabase.getInstance()
    val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/cartlist")
    val databaseReferenceMyOrders = FirebaseDatabase.getInstance().getReference("users/$userId/orderslist")
    databaseReference.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            // Clear the existing cart items
            cartItems.clear()

            // Iterate through the children (key-value pairs)
            for (childSnapshot in snapshot.children) {
                val key = childSnapshot.key // Get the key
                val value = childSnapshot.getValue(CartItem::class.java) // Get the value as a CartItem

                // Add the cart item to the list
                if (value != null) {
                    cartItems.add(value)
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle database error here
            println("Database Error: $error")
        }
    })
    databaseReferenceMyOrders.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            // Clear the existing orders items
            ordersItems.clear()

            // Iterate through the children (key-value pairs)
            for (childSnapshot in snapshot.children) {
                val key = childSnapshot.key // Get the key
                val value = childSnapshot.getValue(MyOrders::class.java) // Get the value as a MyOrders

                // Add the orders item to the list
                if (value != null) {
                    ordersItems.add(value)
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle database error here
            println("Database Error: $error")
        }
    })

    val darkBackgroundColor = Color(0xFF0d0e1c)
    val textureColor = Color(0xFF6a6f9a) // Define your texture color

    val brush = Brush.horizontalGradient(
        colors = listOf( textureColor ,textureColor),
    )

    Column(
        modifier = Modifier
            .background(brush=brush),
        ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(brush = brush)
    ) {
        Text(
            text = "My Orders ",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = colorResource(id = R.color.LightBackgroundColor),
            ),
            modifier = Modifier.padding(bottom = 16.dp, top = 60.dp)
        )
        if (ordersItems.isNotEmpty()) {
            OrderItemList(ordersItems,navController=navController)
        } else {
            Text(text = "No orders placed")
        }

        Text(
            text = "Wishlist",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = colorResource(id = R.color.LightBackgroundColor),
            ),
            modifier = Modifier.padding(top = 16.dp)
        )
        var totalprice = 0
        if (cartItems.isNotEmpty()) {
            // Display cart contents or items here.
            for (cartItem in cartItems) {
                totalprice = totalprice + cartItem.price
            }
            CartItemList(cartItems, navController = navController)
        } else {
            Text(text = "Your Wishlist is empty.")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(
                text = "Total Wishlist Price=Rs $totalprice",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.LightBackgroundColor),
                ),
                modifier = Modifier.align(CenterVertically),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(170.dp))

//            Button(
//                onClick = { navController.navigate("CheckOutScreen") },
//                modifier = Modifier
//                    .padding(vertical = 16.dp)
//            ) {
//                Text(text = "Checkout")
//            }
        }
    }
    }
}

@Composable
fun CartItemRow(cartItem: CartItem,navController:NavController) {
    val database: DatabaseReference = Firebase.database.reference

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor =colorResource(id = R.color.DarkSecondaryColor),
        ),

        ) {


        Row(
            modifier = Modifier
                .padding(16.dp)
                .clickable(onClick = {

                    navController.navigate("BuyNow/${cartItem.id}")

                }),

        ) {
            Column {
                Text(
                    text = cartItem.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.LightBackgroundColor),
                        fontSize = 14.sp
                    ),
                )
                Text(text = "Rs${cartItem.price}",
                    color =colorResource(id = R.color.LightBackgroundColor))
                Spacer(modifier = Modifier.width(300.dp))
            }

//                IconButton(onClick = {
//                    database.child("cartlist").child(cartItem.name).removeValue()
//                })
//                {
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_delete_24),
//                        contentDescription = "e"
//                    )
//                }

        }




            }}


@Composable
fun CartItemList(cartItems: List<CartItem>,navController: NavController) {
    LazyColumn(modifier = Modifier
        .height(300.dp)
    ) {
        items(cartItems) { cartItem ->
            CartItemRow(cartItem,navController = navController )
        }
    }
}
@Composable
fun OrderItemList(orders: List<MyOrders>,navController: NavController) {
    LazyColumn(modifier = Modifier
        .height(300.dp)
    ) {
        items(orders) { orderitem ->
            OrderItem(orderitem, navController =navController )
        }
    }
}


@Composable
fun OrderItem(orders: MyOrders,navController: NavController) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor =colorResource(id = R.color.DarkSecondaryColor),
        ),

        ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable(onClick = {
                navController.navigate("confirmation_screen/${orders.id}")
            }),
        ) {
            Text(
                text = orders.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color =colorResource(id = R.color.LightBackgroundColor),
                    fontSize = 14.sp
                ),
            )
            Row {
                Text(text = orders.payment,

                    color =colorResource(id = R.color.LightBackgroundColor),
                    )
                Spacer(modifier = Modifier.width(190.dp))
                Text(text = orders.status,
                    color =colorResource(id = R.color.LightBackgroundColor),)


            }
        }
    }
}


