package com.BookMart.bookmart.views.myorders

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.domain.models.myorders.CartItem
import com.BookMart.bookmart.domain.models.myorders.MyOrders
import com.BookMart.bookmart.viewModels.myorders.CartViewModel
import com.BookMart.bookmart.viewModels.myorders.MyOrdersViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun MyOrdersScreen(navController: NavHostController) {

    val scrollState = rememberScrollState()
    val cartItems = remember { mutableStateListOf<CartItem>() }
    val ordersItems = remember { mutableStateListOf<MyOrders>() }
    val userId = FirebaseAuth.getInstance().currentUser?.uid // Get the user's ID

    val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/cartlist")
    val databaseReferenceMyOrders = FirebaseDatabase.getInstance().getReference("users/$userId/orderslist")
    databaseReference.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            // Clear the existing cart items
            cartItems.clear()

            // Iterate through the children (key-value pairs)
            for (childSnapshot in snapshot.children) {
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


    Surface(
        color = MaterialTheme.colorScheme.background
        ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.dim_16))
            .verticalScroll(state = scrollState),
    ) {
        Text(
            text = "My Orders ",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                color = MaterialTheme.colorScheme.onPrimary,
            ),
            modifier = Modifier.padding( dimensionResource(id = R.dimen.dim_16))
        )
        if (ordersItems.isNotEmpty()) {
            OrderItemList(ordersItems,navController=navController)
        } else {
            Text(text = "No orders placed",
                color = MaterialTheme.colorScheme.onPrimary)
        }

        Text(
            text = "Wishlist",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.fon_20).value.sp,
                color =MaterialTheme.colorScheme.onPrimary,
            ),
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dim_16))
        )
        var totalprice = 0
        if (cartItems.isNotEmpty()) {
            // Display cart contents or items here.
            for (cartItem in cartItems) {
                totalprice += cartItem.price
            }
            CartItemList(cartItems, navController = navController)
        } else {
            Text(text = "Your Wishlist is empty.")
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_16)))
        Row {
            Text(
                text = "Total Wishlist Price=Rs $totalprice",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.fon_20).value.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                ),
                modifier = Modifier.align(CenterVertically),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dim_170)))

        }
      }
    }
}

@Composable
fun CartItemRow(cartItem: CartItem, navController:NavController) {
     ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.dim_4)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.dim_8)),
        colors = CardDefaults.cardColors(
            containerColor =colorResource(id = R.color.DarkSecondaryColor),
        ),
        ) {

        Row {


        Row(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dim_16))

        ) { Column {
            Image(painter = painterResource(id = cartItem.imageResId),
                contentDescription = "wishlist image",
                modifier = Modifier
                    .height(
                        dimensionResource(id = R.dimen.dim_100)
                    )
                    .width(dimensionResource(id = R.dimen.dim_100)))
        }
            Column {
                Text(
                    text = cartItem.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.LightBackgroundColor),
                        fontSize = dimensionResource(id = R.dimen.fon_14).value.sp
                    ),
                )
                Row {
                    Text(
                        text = "${cartItem.price} X ${cartItem.quantity}Qty =Rs${cartItem.price * cartItem.quantity}",
                        color = colorResource(id = R.color.LightBackgroundColor)
                    )
                }
                Column {
                    Row {
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dim_20)))
                        Button(
                            onClick = {
                                // Trigger the deletion of specific entries here
                                navController.navigate("BuyNow/${cartItem.id}/${cartItem.quantity}")
                            },
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.dim_0),dimensionResource(id = R.dimen.dim_5),dimensionResource(id = R.dimen.dim_0),dimensionResource(id = R.dimen.dim_0))
                                .height(dimensionResource(id = R.dimen.dim_40))
                                .width(150.dp)
                        ) {
                            Text(text = "BuyNow")
                        }
                    }
                }
            }


        }
           }
    }
}

@Composable
fun CartItemList(cartItems: List<CartItem>, navController: NavController) {
    LazyColumn(modifier = Modifier
        .height(dimensionResource(id = R.dimen.dim_300))
    ) {
        items(cartItems) { cartItem ->
            CartItemRow(cartItem,navController = navController )
        }
    }
}
@Composable
fun OrderItemList(orders: List<MyOrders>, navController: NavController) {
    LazyColumn(modifier = Modifier
        .height(dimensionResource(id = R.dimen.dim_300))
    ) {
        items(orders) { orderitem ->
            OrderItem(orderitem, navController =navController )
        }
    }
}


@Composable
fun OrderItem(orders: MyOrders,navController: NavController) {
    val MyOrdersViewModel = viewModel<MyOrdersViewModel>()

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.dim_4)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(id = R.dimen.dim_8)),
        colors = CardDefaults.cardColors(
            containerColor =colorResource(id = R.color.DarkSecondaryColor),
        ),

        ) {
        Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.dim_8))) {

            Column {
                Image(painter = painterResource(id = orders.imageResIdorder),
                    contentDescription = "wishlist image",
                    modifier = Modifier
                        .height(
                            dimensionResource(id = R.dimen.dim_100)
                        )
                        .width(dimensionResource(id = R.dimen.dim_100)))
            }
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dim_16))
                .clickable(onClick = {
                    navController.navigate("confirmation_screen/${orders.id}/${orders.orderkey}/${orders.orderlistkey}")
                }),
        ) {
            Text(
                text = orders.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.LightBackgroundColor),
                    fontSize = dimensionResource(id = R.dimen.fon_14).value.sp
                ),
            )
            Row {
                Text(
                    text = orders.payment,

                    color = colorResource(id = R.color.LightBackgroundColor),
                )
            }
            Text(
                text = orders.status,
                color = colorResource(id = R.color.LightBackgroundColor),
            )
        }
        }
    }
}


