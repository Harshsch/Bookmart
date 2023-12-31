package com.BookMart.bookmart.views.orderflow

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.domain.models.products.ListItem


@Composable
fun Order_Details(navController: NavController, item: ListItem, quantity:Int) {
   // var quantity by remember { mutableIntStateOf(1) }
    val current = "Order"
    val scrollState = rememberScrollState()
    LaunchedEffect(scrollState) {
        // Animate scroll to the top
        scrollState.scrollTo( 10000)
    }

    Surface(modifier = Modifier
        .padding(16.dp),
        color=MaterialTheme.colorScheme.background
    ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(16.dp)
                .verticalScroll(state = scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ProgressBar(steps = listOf("Address", "Order", "Payment"), currentStep = current)

                Spacer(modifier = Modifier.height(22.dp))
                Text(
                    text = "Product Information",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Card(modifier = Modifier.padding(8.dp)) {
                    Row {
                        Text("Product Name:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp)
                        Text(item.name)

                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Text("Description: ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp)
                        Text(item.description)

                    }


                }

                var total=item.price*quantity
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${item.price} X ${quantity }Qty =Rs${total}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp)

                    Spacer(modifier = Modifier.width(50.dp))
                    Button(onClick = {
                        navController.navigate("Payment/${item.id}/$quantity")
                    })
                    {
                        Text(text = "Contiue")

                    }
                }
            }
        }
    }



