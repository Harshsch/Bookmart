package com.BookMart.bookmart.views.orderflow

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavHostController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.domain.models.myorders.CartItem
import com.BookMart.bookmart.domain.models.products.ListItem
import com.BookMart.bookmart.viewModels.myorders.CartViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun BookDisplay(navController: NavHostController, item: ListItem) {
    // Initialize Firebase Auth
    var quantity by remember { mutableIntStateOf(1) }
    val auth: FirebaseAuth = Firebase.auth
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(scrollState) {
        // Animate scroll to the top
        scrollState.scrollTo( 10000)
    }
    Box(modifier = Modifier
        .background(colorResource(id = R.color.DarkSurfaceColor))
        .padding(dimensionResource(id = R.dimen.dim_16))

    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(id = R.dimen.dim_10)
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.dim_0)),
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.dim_16))
                    .verticalScroll(state = scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = "Book Cover",
                )
                Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.dim_15)),
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.dim_16)),
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.fon_16).value.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF19191B),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        text = item.department,
                        modifier = Modifier,
                        style = TextStyle(
                            fontSize = dimensionResource(id = R.dimen.fon_18).value.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF19191B),
                            textAlign = TextAlign.Center,
                        ),

                    )
                }
                Text(
                    text = item.description,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.dim_16)),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.fon_14).value.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF9D9D9D),
                    )
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_32)))
                Row {
                    Text("Price per Unit:  ")
                    Text("₹${item.price} ")

                }

                Row(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dim_8)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Quantity: ",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    IconButton(
                        onClick = { if (quantity > 1) quantity-- },
                        modifier = Modifier.size(dimensionResource(id = R.dimen.dim_32))
                    ) {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.headlineSmall,

                            )
                    }
                    Text(
                        "$quantity",
                        style = MaterialTheme.typography.headlineSmall,
                    )
                    IconButton(
                        onClick = { quantity++ },
                        modifier = Modifier.size(dimensionResource(id = R.dimen.dim_32))
                    ) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
                var totalprice=item.price *quantity
                Text(
                    text = "₹${totalprice}", // Assuming item.price is the price of the book
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dim_8)),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.fon_18).value.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF19191B),
                        textAlign = TextAlign.Center,
                    )
                )
                val cartViewModel = viewModel<CartViewModel>()
                Row {
                    Button(
                        onClick = {
                            // Check if user is signed in (non-null) and update UI accordingly.
                            val currentUser = auth.currentUser
                            if (currentUser != null) {
                                val newItem = CartItem(
                                    id = item.id,
                                    name = item.name,
                                    price = item.price,
                                    quantity=quantity,
                                    imageResId = item.imageResId,

                                )
                                Toast.makeText(context, "Added to Wishlist", Toast.LENGTH_SHORT).show()
                                // Add the item to the cart using the ViewModel
                                cartViewModel.addItemToCart(newItem)
                            } else {
                                navController.navigate("Signup")
                            }
                        },
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.dim_173))
                            .height(dimensionResource(id = R.dimen.dim_55))
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.dim_26))
                            ),
                    ) {
                        Text(text = "Wishlist")
                    }
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dim_10)))
                    Button(
                        onClick = {
                            // Check if user is signed in (non-null) and update UI accordingly.
                            val currentUser = auth.currentUser
                            if (currentUser != null) {
                                navController.navigate("BuyNow/${item.id}/$quantity")
                                //Toast.makeText(context, "Already logged in", Toast.LENGTH_SHORT).show()
                            } else {
                                navController.navigate("Signup")
                            }
                        },
                        modifier = Modifier
                            .width(dimensionResource(id = R.dimen.dim_173))
                            .height(dimensionResource(id = R.dimen.dim_55))
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.dim_26))
                            )
                    ) {
                        Text(text = "Buy")
                    }
                }
            }
        }
    }
}
@Composable
fun QuantitySelector(
    initialQuantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    var quantity by remember { mutableIntStateOf(initialQuantity) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = {
                if (quantity > 1) {
                    quantity--
                    onQuantityChange(quantity)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Decrease Quantity"
            )
        }

        Text(
            text = quantity.toString(),
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dim_16)),
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(id = R.dimen.fon_18).value.sp
        )

        IconButton(
            onClick = {
                quantity++
                onQuantityChange(quantity)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Increase Quantity"
            )
        }
    }
}

