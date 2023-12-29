package com.BookMart.bookmart.views.orderflow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.domain.models.myorders.Address
import com.BookMart.bookmart.domain.models.myorders.BuyNowData
import com.BookMart.bookmart.domain.models.myorders.MyOrders
import com.BookMart.bookmart.domain.models.products.ListItem
import com.BookMart.bookmart.viewModels.myorders.MyOrdersViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun Payment(navController: NavController, item: ListItem, quantity:Int) {

    val current = "Payment"
    var orderkey: String? =null

    val userId = FirebaseAuth.getInstance().currentUser?.uid

    val currentUser = FirebaseAuth.getInstance().currentUser
    val databaseReference =
        FirebaseDatabase.getInstance().getReference("users/$userId/buy_now_data")


    var defaultAddress by remember { mutableStateOf<Address?>(null) }
    val defaultdatabaseReference =
        FirebaseDatabase.getInstance().getReference("users/$userId/defaultAddress")

    val scrollState = rememberScrollState()
    LaunchedEffect(scrollState) {
        // Animate scroll to the top
        scrollState.scrollTo( 10000)
    }
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

    val buyNowData = currentUser?.displayName?.let {
        defaultAddress?.let { it1 ->
            BuyNowData(
                name = it,
                streetAddress = it1.streetAddress,
                city = it1.city,
                mobilenumber = it1.mobileNumber.toString(),
                productname = item.name,
                priceperunit = item.price,
                quantity = quantity,
                totalprice = "Rs ${quantity * item.price}"
            )
        }
    }

    Box(modifier = Modifier
        .background(colorResource(id = R.color.DarkSurfaceColor))
        .padding(dimensionResource(id = R.dimen.dim_0), dimensionResource(id = R.dimen.dim_50), dimensionResource(id = R.dimen.dim_0), dimensionResource(id = R.dimen.dim_50))

    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(id = R.dimen.dim_10)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.dim_16), dimensionResource(id = R.dimen.dim_16), dimensionResource(id = R.dimen.dim_16), dimensionResource(id = R.dimen.dim_16)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.dim_16))
                .verticalScroll(state = scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ProgressBar(steps = listOf("Address", "Order", "Payment"), currentStep = current)

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_22)))
            Text(
                text = "Select Payment Method",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dim_16))
            )

            var selectedPaymentMethod by remember { mutableStateOf<PaymentMethod?>(null) }

            var selectedmethod = ""

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.dim_16)),
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

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_32)))
            Price(quantity = quantity, item = item)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_32)))

            val MyOrdersViewModel = viewModel<MyOrdersViewModel>()
                val orderlistkey=MyOrdersViewModel.orderkey

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

                        }
                    }


                    val newChildRef: DatabaseReference = databaseReference.push()
                    newChildRef.setValue(buyNowData)

                    orderkey=newChildRef.getKey()

                    // Navigate to the confirmation screen.
                    val newItem = MyOrders(

                        id = item.id,
                        name = item.name,
                        payment = "Payment: Pending ",
                        status = "5 days left",
                        imageResIdorder=item.imageResId,
                        orderkey= orderkey.toString(),
                        orderlistkey= orderlistkey.toString()
                    )
                    // Add the item to the cart using the ViewModel
                    MyOrdersViewModel.addItemToOrders(newItem)
                    navController.navigate("confirmation_screen/${item.id}/${orderkey}/${orderlistkey}")


                },
                enabled = (selectedPaymentMethod != null) &&
                        (defaultAddress?.streetAddress != null) &&
                        (defaultAddress?.city != null),

                ) {
                Text(text = "Confirm Order ")
            }
        }
    }}
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
            .height(dimensionResource(id = R.dimen.dim_50))
            .background(
                color = if (isSelected) Color.Gray else Color.White,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.dim_8))
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

