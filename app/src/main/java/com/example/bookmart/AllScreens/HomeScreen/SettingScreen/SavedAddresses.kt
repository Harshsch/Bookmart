package com.example.bookmart.AllScreens.HomeScreen.SettingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    //var MobileNumberValidationsPassed = mutableStateOf(false)
    var streetAddress by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var Mobile_Number by remember { mutableStateOf("") }

    var isConfirmButtonPressed by remember { mutableStateOf(false) }
    val userId = FirebaseAuth.getInstance().currentUser?.uid
// Get a reference to the database
    val database = FirebaseDatabase.getInstance()

    val databaseReference =FirebaseDatabase.getInstance().getReference("users/$userId/Saved_address")

    val currentUser = FirebaseAuth.getInstance().currentUser
    val savedaddress = remember { mutableStateListOf<Address>() }

// Create an instance of your data class
    databaseReference.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            // Clear the existing cart items
            savedaddress.clear()

            for (childSnapshot in snapshot.children) {
                val value = childSnapshot.getValue(Address::class.java) // Get the value as a CartItem

                if (value != null) {
                    savedaddress.add(value)
                }
            }
        }
        override fun onCancelled(error: DatabaseError) {
            println("Database Error: $error")
        }
    })
    val useraddress = currentUser?.displayName?.let {
        Address(
            name = it,
            streetAddress = streetAddress,
            city = city,
            mobileNumber=Mobile_Number
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()

            .background(colorResource(id = R.color.DarkPrimaryColor))
    ){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp,80.dp,16.dp,16.dp)
            .background(colorResource(id = R.color.DarkPrimaryColor))
    )
    {
        AddressTextField(
            "Residential Address",
            streetAddress
        ) { streetAddress = it }
        AddressTextField(
            "City,State,Postal Code",
            city
        ) { city = it }
        AddressTextField(
            "Mobile Number",
            Mobile_Number
        ) { Mobile_Number = it }




        Button(onClick = {
            isConfirmButtonPressed = true
                databaseReference.push().setValue(useraddress)
            })
        {
            Text(text = "+ Add a new address")
        }
        if (savedaddress.isNotEmpty()) {
            // Display cart contents or items here.

            AddressItemList(savedaddress, navController = navController)
        } else {
            Text(text = "Your Wishlist is empty.")
        }
    }


    }}
@Composable
fun AddressItem(adress: Address, navController:NavController) {


    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.DarkSecondaryColor),
        ),

        ) {
        Column(
            modifier = Modifier
                .padding(16.dp)

                //.background(colorResource(id = R.color.DarkSurfaceColor))
            ) {

            Text(text = adress.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.LightBackgroundColor),

                    ),)

                Text(text = adress.streetAddress,
                    color = colorResource(id = R.color.LightBackgroundColor),

                    )

                Text(text = adress.city,color = colorResource(id = R.color.LightBackgroundColor),

                    )


            Text(text = "${adress.mobileNumber}",
                    color = colorResource(id = R.color.LightBackgroundColor),

                )}}

}
@Composable
fun AddressItemList(address: List<Address>, navController: NavController) {
    LazyColumn(modifier = Modifier
        .height(300.dp)
    ) {
        items(address) { addresses ->
            AddressItem(addresses, navController = navController )
        }
    }
}


