package com.example.bookmart.AllScreens.HomeScreen.OrderPath

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookmart.R
import com.example.bookmart.data.ListItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


@Composable
 fun OrderPlacedPage(navController: NavController, item: ListItem,orderkey:String,orderlistkey:String) {

    val currentUser = FirebaseAuth.getInstance().currentUser
    val uid = currentUser?.uid
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val orderIdToDelete = orderkey
    val orderlistIdToDelete=orderlistkey
    val userId = uid
    fun deleteSpecificEntries() {

        val orderslistRef = FirebaseDatabase.getInstance().getReference("users/$userId/orderslist/$orderlistIdToDelete")

        val buyNowDataRef = FirebaseDatabase.getInstance().getReference("users/$userId/buy_now_data/$orderIdToDelete")


        orderslistRef.removeValue()
            .addOnSuccessListener {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

        }

        // Delete the "buy_now_data" entry
        buyNowDataRef.removeValue().addOnSuccessListener {
           // Toast.makeText(context, "$orderIdToDelete  $orderkey", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
           // Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 50.dp, 16.dp, 16.dp),
    )
    {
        Row(horizontalArrangement =Arrangement.End,
            verticalAlignment =Alignment.Top) {
            Button(
                onClick = {
                    navController.navigate("home_route")
                },
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors( Color(0xFF333333))
            ){
                Image(painter =painterResource(id =R.drawable.baseline_close_24 ) , contentDescription =null )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            // Animated checkmark icon
            AnimatedCheckmarkIcon()

            Spacer(modifier = Modifier.height(16.dp))

            // Order ID
            Text(
                text = "Order Confirmed",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display the order ID
            Text(
                text = "Order ID: ${uid + item.id}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Email is sent to you   ",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            //Spacer(modifier = Modifier.height(32.dp))
            if (currentUser != null) {
                Text(
                    text = "${currentUser.email} ",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Animated image (You can replace this with your own image)
            AnimatedOrderImage(item)

            Spacer(modifier = Modifier.height(32.dp))


                Button(
                    onClick = {
                        // Trigger the deletion of specific entries here
                        deleteSpecificEntries()
                        navController.navigate("home_route")
                    }
                ) {
                    Text(text = "Cancle Orders")
                }

            Button(
                onClick = { navController.navigate("home_route")}, // Navigate back to shopping
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Continue Shopping")
            }

        }
    }

}

@Composable
fun AnimatedCheckmarkIcon() {
    Image(
        painter = painterResource(id = R.drawable.baseline_verified_24) , // Placeholder image
        contentDescription = null, // Content description for accessibility
        modifier = Modifier.size(80.dp) // Adjust the size as needed
    )
}

@Composable
fun AnimatedOrderImage(item: ListItem) {
    Image(
        painter = painterResource(id =item.imageResId), // Placeholder image
        contentDescription = null, // Content description for accessibility
        modifier = Modifier.fillMaxSize() // Fill the available space
    )
}

