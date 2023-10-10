package com.example.bookmart.AllScreens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bookmart.AllScreens.HomeScreen.UserProfile.isFormValid
import com.example.bookmart.BuyNowData
import com.example.bookmart.ListItem
import com.example.bookmart.R
import com.example.bookmart.data.AddToCart.Address
import com.example.bookmart.data.AddToCart.MyOrders
import com.example.bookmart.data.AddToCart.MyOrdersViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun BuyNowPage(navController: NavController,item: ListItem) {
    var streetAddress by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf(1) }
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    var isConfirmButtonPressed by remember { mutableStateOf(false) }
// Get a reference to the database
    val database = FirebaseDatabase.getInstance()

    val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/buy_now_data")

    var defaultAddress by remember { mutableStateOf<Address?>(null) }

    val defaultdatabaseReference =
        FirebaseDatabase.getInstance().getReference("users/$userId/defaultAddress")

    LaunchedEffect(Unit) {
        // Add a ValueEventListener to fetch and update the data
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Retrieve the Address object from Firebase
                val value = snapshot.getValue(Address::class.java)
                if (value != null) {
                    defaultAddress = value
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error here
                println("Database Error: $error")
            }
        }
        defaultdatabaseReference.addValueEventListener(valueEventListener)
    }
// Create an instance of your data class
    val buyNowData = currentUser?.displayName?.let {
        defaultAddress?.let { it1 ->
            BuyNowData(
                name = it,
                streetAddress = it1.streetAddress,
                city = it1.city,
                mobilenumber= it1.mobileNumber.toString(),
                productname=item.name,
                priceperunit= item.price,
                quantity=quantity,
                totalprice = "Rs ${ quantity * item.price }"
            )
        }
    }
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.DarkSurfaceColor)),
    ) {

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
        ) {
            Text(
                text = "Order Details",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 30.dp)
            )


            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                if (currentUser != null) {
                    Text(
                        text = "Customer name:${currentUser.displayName}",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                // Address Form
                Text(
                    text = "Shipping Address",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                    AddressCard(
                        streetaddress = defaultAddress?.streetAddress, city = defaultAddress?.city, mobilenumber = defaultAddress?.mobileNumber.toString(), navController = navController
                    )
//                //AddressTextField("Name", name) { name = it }
//                AddressTextField("Residential Address", streetAddress) { streetAddress = it }
//                AddressTextField("City,State,Postal Code", city) { city = it }

                Spacer(modifier = Modifier.height(12.dp))


                // Product Information
                Text(
                    text = "Product Information",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    modifier = Modifier,


                    ) {
                    Column(modifier = Modifier.padding(8.dp)) {


                        Row {
                            Text("Product Name:")
                            Text("${item.name}")

                        }

                        Row {
                            Text("Description: ")
                            Text("${item.description}")

                        }

                        Row {
                            Text("Price per Unit:  ")
                            Text("₹${item.price} ")

                        }



                        Row(
                            modifier = Modifier.padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Quantity: ",
                                style = MaterialTheme.typography.headlineSmall,
                            )
                            IconButton(
                                onClick = { if (quantity > 1) quantity-- },
                                modifier = Modifier.size(32.dp)
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
                                modifier = Modifier.size(32.dp)
                            ) {
                                Text(
                                    text = "+",
                                    style = MaterialTheme.typography.headlineMedium,
                                )
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(32.dp))

                // Confirm Order and Payment

                Text(
                    text = "Select Payment Method",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                var selectedPaymentMethod by remember { mutableStateOf<PaymentMethod?>(null) }

                var selectedmethod = ""

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PaymentOption(
                        text = "Google Pay",
                        selectedPaymentMethod = selectedPaymentMethod,
                        onPaymentMethodSelected = { selectedPaymentMethod = it },
                        paymentMethod = PaymentMethod.GPAY
                    )
                    PaymentOption(
                        text = "Cash on Delivery",
                        selectedPaymentMethod = selectedPaymentMethod,
                        onPaymentMethodSelected = { selectedPaymentMethod = it },
                        paymentMethod = PaymentMethod.COD
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
                Price(quantity = quantity, item = item)
                Spacer(modifier = Modifier.height(32.dp))

                val MyOrdersViewModel = viewModel<MyOrdersViewModel>()

                // Confirm Order and Payment
                Button(
                    onClick = {

                            // Handle payment processing based on the selected method.
                            when (selectedPaymentMethod) {
                                PaymentMethod.GPAY -> {
                                    selectedmethod = "UPI"
                                    // Handle Google Pay payment
                                }

                                PaymentMethod.COD -> {
                                    selectedmethod = "COD"
                                }

                                else -> {
                                    null
                                }
                            }
                            val newItem = MyOrders(

                                name = item.name,
                                payment = "Payment: Pending ",
                                status = "5 days left"
                            )
                            // Add the item to the cart using the ViewModel
                            MyOrdersViewModel.addItemToOrders(newItem)

                            isConfirmButtonPressed = true
                            if (isConfirmButtonPressed) {
                                databaseReference.push().setValue(buyNowData)
                            }
                            // Navigate to the confirmation screen.

                            navController.navigate("confirmation_screen/${item.id}")



                    },
                    enabled = selectedPaymentMethod != null &&
                            defaultAddress?.streetAddress!=null&&
                            defaultAddress?.city!=null &&
                            defaultAddress?.mobileNumber.toString()!=null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Confirm Order ")
                }
            }

        }
    }
    }
@Composable
fun Price(quantity:Int,item: ListItem)
{
    val totalPrice = quantity * item.price
    Text(
        text = "Total Price: ₹$totalPrice",
        style = MaterialTheme.typography.headlineMedium,

    )
}
@Composable
fun PaymentOption(
    text: String,
    selectedPaymentMethod: PaymentMethod?,
    onPaymentMethodSelected: (PaymentMethod) -> Unit,
    paymentMethod: PaymentMethod
) {
    val isSelected = paymentMethod == selectedPaymentMethod

    Box(
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
            .background(
                color = if (isSelected) Color.Gray else Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onPaymentMethodSelected(paymentMethod)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}
enum class PaymentMethod {
    GPAY,
    COD
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .padding(8.dp),
        label = { Text(text = label) },


        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onValueChange(it)
        },


    )
}
@Composable
fun AddressCard(
    streetaddress: String?,
    city: String?,
    mobilenumber: String?,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Address",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            if (streetaddress != null) {
                Text(
                    text = streetaddress,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            } else {
                Text(
                    text = "Street Address not available",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            if (city != null) {
                Text(
                    text = city,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            } else {
                Text(
                    text = "City not available",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
            if (mobilenumber != null) {
                Text(
                    text = mobilenumber,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            } else {
                Text(
                    text = "Mobile Number not available",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate("SavedAddress")


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Change")
            }
        }
    }
}

// Handle address change click

