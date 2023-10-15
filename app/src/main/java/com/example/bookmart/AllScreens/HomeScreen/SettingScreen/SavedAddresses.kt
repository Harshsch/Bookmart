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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookmart.NormalTextComponent
import com.example.bookmart.R
import com.example.bookmart.data.AddToCart.Address
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun SavedAddressesScreen(navController: NavController) {
//    val UniqueiddatabaseReference = FirebaseDatabase.getInstance().getReference("addresses")
//    val newAddressRef = UniqueiddatabaseReference.push()
//    val addressId = newAddressRef.key


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

    Column(
        modifier = Modifier
            .padding(0.dp, 60.dp, 0.dp, 50.dp)
            .fillMaxSize()
            .background(colorResource(id = R.color.DarkPrimaryColor))

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 0.dp, 16.dp, 0.dp)
                .background(colorResource(id = R.color.DarkPrimaryColor))

        ) {
            item {
            NormalTextComponent(value = "Step 1:- Add new address ")
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

                val UniqueiddatabaseReference = FirebaseDatabase.getInstance().getReference("addresses")
                val newAddressRef = UniqueiddatabaseReference.push()
                val addressId = newAddressRef.key
                if (streetAddress.isNotEmpty() && city.isNotEmpty() && Mobile_Number.isNotEmpty()) {
                    isConfirmButtonPressed = true
                    val useraddress = currentUser?.displayName?.let {
                        addressId?.let { it1 ->
                            Address(
                                id = it1,
                                name = it,
                                streetAddress = streetAddress,
                                city = city,
                                mobileNumber = Mobile_Number,
                                // isDefault = true // Set this to true for the default address
                            )
                        }
                    }
                    databaseReference.push().setValue(useraddress)

                    // Clear form fields

                } else {
                    Toast.makeText(context, "Enter valid address", Toast.LENGTH_SHORT).show()
                }
                navController.navigate("SavedAddress")
            }) {
                Text(text = "+ Add a new address")
            }
               NormalTextComponent(value = "Step 2:- Select Address   ")

            if (savedaddress.isNotEmpty()) {
                AddressItemList(savedaddress, navController = navController)
            } else {
                Text(text = "Your Address is empty.")
            }
        }
    }
    }
}
@Composable
fun AddressItem(address: Address, navController: NavController) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val databaseReference =
        FirebaseDatabase.getInstance().getReference("users/$userId/defaultAddress")
    var defaultAddress by remember { mutableStateOf<Address?>(null) }

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
        databaseReference.addValueEventListener(valueEventListener)
}

    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {

                databaseReference.setValue(address)

            },
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.DarkSecondaryColor)),
    ) {
        Row(modifier = Modifier
            //.clickable {}
        )
        {
            if (defaultAddress?.id ==address.id) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected Icon",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(26.dp)
                        .align(Alignment.CenterVertically),
                    tint = Color.Green
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Circle ,
                    contentDescription = "Selected Icon",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(24.dp)
                        .align(Alignment.CenterVertically),
                    tint = Color.Black
                )
            }


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
fun AddressItemList(address: List<Address>, navController: NavController) {
    LazyColumn(modifier = Modifier.height(300.dp)) {
        items(address) { addresses ->
            AddressItem(addresses, navController = navController)
        }
    }

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
   // val localFocusManager = LocalFocusManager.current

   /// Row {

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
            }
        )

}
