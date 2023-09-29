package com.example.bookmart.AllScreens.HomeScreen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bookmart.ListItem

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



@Composable
fun BookDisplay(navController: NavHostController,item: ListItem) {
    lateinit var auth: FirebaseAuth
    // Initialize Firebase Auth
    auth = Firebase.auth
    val context = LocalContext.current

    Box (modifier = Modifier.padding(16.dp)){
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp,50.dp,16.dp,16.dp),


            )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(id = item.imageResId), contentDescription = "null",)
                Column(modifier = Modifier.padding(15.dp)) {


                    Text(
                        text = item.name,
                        Modifier
                            .width(155.dp)
                            .height(24.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF19191B),
                            textAlign = TextAlign.Center,
                        )
                    )
                    Text(
                        text = " V.S Bagad",
                        Modifier
                            .width(87.dp)
                            .height(24.dp),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF9D9D9D),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
                Text(
                    text = item.author,
                    Modifier
                        .width(156.dp)
                        .height(27.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF19191B),
                        textAlign = TextAlign.Center,
                    )
                )

                Text(
                    text = "J.D. Salinger was an American writer, best known for his 1951 novel The Catcher in the Rye. Before its publi cation, Salinger published several short stories in Story magazine",
                    Modifier
                        .width(356.dp)
                        .height(84.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF9D9D9D),
                    )
                )
                Row {
                    Button(
                        onClick = {
                            // Check if user is signed in (non-null) and update UI accordingly.
                            val currentUser = auth.currentUser
                            if (currentUser != null) {
                                Toast.makeText(context, "Already logedin", Toast.LENGTH_SHORT).show()


                            }
                            else{
                                navController.navigate("Signup")
                            }
                        },
                        modifier = Modifier
                            .width(173.dp)
                            .height(55.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(size = 26.dp)
                            ),


                        ) {
                        Text(text = "Add to Cart")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = {
                            // Check if user is signed in (non-null) and update UI accordingly.
                            val currentUser = auth.currentUser
                            if (currentUser != null) {
                                Toast.makeText(context, "Already logedin", Toast.LENGTH_SHORT).show()


                            }
                            else{
                                navController.navigate("Signup")
                            }
                        },
                        modifier = Modifier
                            .width(173.dp)
                            .height(55.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(size = 26.dp)
                            )

                    ) {
                        Text(text = "Buy")
                    }
                }
            }
        }
    }
}