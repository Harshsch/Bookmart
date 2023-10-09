package com.example.bookmart.AllScreens.HomeScreen.SettingScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookmart.AllScreens.HomeScreen.AddressTextField
import com.example.bookmart.R
import com.example.bookmart.data.AddToCart.Address
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
@Composable
fun SavedAddressesScreen(navController: NavController) {
    var id by remember { mutableStateOf(0) }
    var streetAddress by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var Mobile_Number by remember { mutableStateOf("") }
    var isConfirmButtonPressed by remember { mutableStateOf(false) }
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val databaseReference =
        FirebaseDatabase.getInstance().getReference("users/$userId/Saved_address")
    val currentUser = FirebaseAuth.getInstance().currentUser
    val savedaddress = remember { mutableStateListOf<Address>() }

    databaseReference.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            savedaddress.clear()
            for (childSnapshot in snapshot.children) {
                val value = childSnapshot.getValue(Address::class.java)
                if (value != null) {
                    savedaddress.add(value)
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            println("Database Error: $error")
        }
    })

    var selectedAddress by remember { mutableStateOf<Address?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.DarkPrimaryColor))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 80.dp, 16.dp, 16.dp)
                .background(colorResource(id = R.color.DarkPrimaryColor))
        ) {
            AddressTextField("Residential Address", streetAddress) { value ->
                streetAddress = value
            }
            AddressTextField("City, State, Postal Code", city) { value ->
                city = value
            }
            AddressTextField("Mobile Number", Mobile_Number) { value ->
                Mobile_Number = value
            }

            val context = LocalContext.current
            Button(onClick = {
                id++
                if (streetAddress.isNotEmpty() && city.isNotEmpty() && Mobile_Number.isNotEmpty()) {
                    isConfirmButtonPressed = true
                    val useraddress = currentUser?.displayName?.let {
                        Address(
                            id = id,
                            name = it,
                            streetAddress = streetAddress,
                            city = city,
                            mobileNumber = Mobile_Number,
                            default = false
                        )
                    }
                    databaseReference.push().setValue(useraddress)
                    // Clear form fields
                    streetAddress = ""
                    city = ""
                    Mobile_Number = ""
                } else {
                    Toast.makeText(context, "Enter valid address", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text(text = "+ Add a new address")
            }

            if (savedaddress.isNotEmpty()) {
                AddressItemList(savedaddress, selectedAddress, navController = navController)
            } else {
                Text(text = "Your Wishlist is empty.")
            }
        }
    }
}

@Composable
fun AddressItem(address: Address, selectedAddress: Address?, navController: NavController) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val databaseReference =
        FirebaseDatabase.getInstance().getReference("users/$userId/defaultAddress")

    val isSelected = selectedAddress == address

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = {
                if (isSelected) {
                    // Deselect the address if it's already selected
                    //selectedAddress = null
                    // Update the Firebase database with the new default address ID (null to clear selection)
                    databaseReference.setValue(null)
                } else {
                    // Select the clicked address
                    //selectedAddress = address
                    // Update the Firebase database with the new default address ID
                    databaseReference.setValue(address.id)
                }
            }),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.DarkSecondaryColor)),
    ) {
        Row {
            Icon(
                imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.Circle,
                contentDescription = "Selected Icon",
                tint = colorResource(id = R.color.LightBackgroundColor),
                modifier = Modifier.size(24.dp),
            )

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = address.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.LightBackgroundColor),
                    ),
                )

                Text(
                    text = address.streetAddress,
                    color = colorResource(id = R.color.LightBackgroundColor),
                )

                Text(
                    text = address.city,
                    color = colorResource(id = R.color.LightBackgroundColor),
                )

                address.mobileNumber?.let {
                    Text(
                        text = it,
                        color = colorResource(id = R.color.LightBackgroundColor),
                    )
                }
            }
        }
    }
}

@Composable
fun AddressItemList(address: List<Address>, selectedAddress: Address?, navController: NavController) {
    LazyColumn(modifier = Modifier.height(300.dp)) {
        items(address) { addresses ->
            AddressItem(addresses, selectedAddress, navController = navController)
        }
    }
}
