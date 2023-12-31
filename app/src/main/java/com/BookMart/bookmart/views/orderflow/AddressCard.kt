package com.BookMart.bookmart.views.orderflow

import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.domain.models.myorders.Address
import com.BookMart.bookmart.domain.models.products.ListItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun AddressCard(navController: NavController, item: ListItem, quantity:Int)
{
    val scrollState = rememberScrollState()
    val current = "Address"
    val context = LocalContext.current

    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val currentUser = FirebaseAuth.getInstance().currentUser
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

        Surface(modifier = Modifier,
            color=MaterialTheme.colorScheme.background
        ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(state = scrollState),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ProgressBar(steps = listOf("Address", "Order", "Payment"), currentStep = current)

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_12)))

                    if (currentUser != null) {
            Text(
                text = "Customer name:${currentUser.displayName}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dim_16))
            )
        }
        // Address Form
        Text(
            text = "Shipping Address",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dim_8))
        )

            AddressCard(
                streetaddress = defaultAddress?.streetAddress,
                city = defaultAddress?.city,
                mobilenumber = defaultAddress?.mobileNumber.toString(),
                navController = navController
            )
                    var total=item.price*quantity
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "${item.price} X ${quantity }Qty =Rs${total}",
                            fontWeight = FontWeight.Bold,
                            fontSize = dimensionResource(id = R.dimen.fon_18).value.sp)

                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dim_50)))

                        Button(onClick = {
                            if((defaultAddress?.streetAddress != null) &&
                                (defaultAddress?.city != null))
                            {                            navController.navigate("order_details/${item.id}/$quantity")
                            }
                            else{
                                Toast.makeText(context, "Change Address", Toast.LENGTH_SHORT).show()
                            }
                        },
                            )
                        {
                            Text(text = "Contiue")

                        }
                    }

    }
}
}
@Composable
fun ProgressBar(steps: List<String>, currentStep: String) {
    val currentStepIndex = steps.indexOf(currentStep)

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        steps.forEachIndexed { index, step ->
            if (index > 0) {
                Spacer(
                    modifier = Modifier
                        .align(alignment = CenterVertically)
                        .weight(1f)
                        .height(dimensionResource(id = R.dimen.dim_2))
                        .offset(x = dimensionResource(id = R.dimen.dim_1)) // Adjust the offset as needed
                        .background(
                            if (index <= currentStepIndex) Color.Green else Color.Gray
                        )
                )
            }
            StepIndicator(
                text = step,
                isCurrent = step == currentStep,
                isCompleted = steps.indexOf(step) < currentStepIndex
            )
        }
    }
}

@Composable
fun StepIndicator(text: String, isCurrent: Boolean, isCompleted: Boolean) {
    val indicatorColor = if (isCurrent) Color.Blue else if (isCompleted) Color.Green else Color.Gray
    val textColor = if (isCurrent) Color.Blue else if (isCompleted) Color.Green else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.dim_24))
                .background(indicatorColor, CircleShape)

        ) {
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize()

                )
            }
        }
        Text(
            text = text,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun AddressCard(
    streetaddress: String?,
    city: String?,
    mobilenumber: String?,
    navController: NavController
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.dim_5)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dim_16)),
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dim_16))
        ) {
            Text(
                text = "Address",
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen.fon_18).value.sp
            )
            if (streetaddress != null) {
                Text(
                    text = streetaddress,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.fon_18).value.sp
                )
            } else {
                Text(
                    text = "Street Address not available",
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.fon_18).value.sp
                )
            }
            if (city != null) {
                Text(
                    text = city,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.fon_18).value.sp
                )
            } else {
                Text(
                    text = "City not available",
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.fon_18).value.sp
                )
            }
            if (mobilenumber != null) {
                Text(
                    text = mobilenumber,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.fon_18).value.sp
                )
            } else {
                Text(
                    text = "Mobile Number not available",
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.fon_18).value.sp
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_16)))
            Button(
                onClick = {
                    navController.navigate("SavedAddress")


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.dim_50))
            ) {
                Text(text = "Change")
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