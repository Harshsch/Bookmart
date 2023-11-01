package com.BookMart.bookmart

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.BookMart.bookmart.data.ListItem
import com.BookMart.bookmart.data.itemList
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

fun isInternetConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)

    return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
}

@Composable
fun HomeScreen(navController: NavHostController)
{

    val context = LocalContext.current
    FirebaseApp.initializeApp(context)
    val textureColor = Color(0xFF6a6f9a) // Define your texture color
    val brush = Brush.horizontalGradient(
        colors = listOf( textureColor ,textureColor),
    )
    val currentUser = FirebaseAuth.getInstance().currentUser
    if(!isInternetConnected(context))
    {
        navController.navigate("no_internet")
    }
    else{
    Column(
        modifier = Modifier
            .background(brush=brush)
            .padding(16.dp)
    ){
        var isCardVisible by remember { mutableStateOf(true) }

        if (isCardVisible) {
            // Automatically close the card after 5 seconds
//            LaunchedEffect(isCardVisible) {
//                delay(5000) // Adjust the delay duration as needed (5000 milliseconds = 5 seconds)
//                isCardVisible = false
//            }
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    ,
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.DarkSecondaryColor),
                    ),
                ) {

                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = stringResource(id = R.string.ready_for_exam),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(),
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Normal
                            ), color = colorResource(id = R.color.LightBackgroundColor),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            DepartmentBooks(navController = navController)
        }
    }}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentBooks(navController: NavHostController) {
    var text by remember { mutableStateOf("") }

    val lazyColumnState = rememberLazyListState()
    val textState = rememberUpdatedState(text)
    Spacer(modifier = Modifier.height(12.dp))
    TextField(

        value = text,
        onValueChange = {
                newText ->
            text = newText

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp))
        ,

        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Search"
                )
            }
        },

    )

    AnimatedVisibility ( textState.value.isNotEmpty()) {
        LaunchedEffect(lazyColumnState) {
            // Animate scroll to the top
            lazyColumnState.animateScrollToItem(index = 0)
        }
    }

    val searchQuery = text
    fun searchBooksByName(query: String): List<ListItem> {
        val lowercaseQuery = query.toLowerCase()
        return itemList.filter { it.name.toLowerCase().contains(lowercaseQuery) }
    }

    val results = searchBooksByName(searchQuery)

    LazyColumn(state = lazyColumnState)
    {
        item {
            Text(
                text = "Search Results",
                Modifier.padding(10.dp)
                ,

                 style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color =colorResource(id = R.color.LightBackgroundColor),

                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            LazyRow {
                items(results) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
        }
        item {
            Text(
                text = "First Year",
                Modifier.padding(10.dp),

                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color =colorResource(id = R.color.LightBackgroundColor),

                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            LazyRow {
                items(itemList.filter { it.department == "First Year" }) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
        }

        item {
            Text(
                text = "Computer Science ",
                Modifier.padding(10.dp),

                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color =colorResource(id = R.color.LightBackgroundColor),

                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            Text(
                text = "SE ",
                Modifier.padding(10.dp),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFCCCCCC),
                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            LazyRow {
                items(itemList.filter { it.department=="Computer Science"&& it.year==2 }) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
        }
        item {
            Text(
                text = "TE ",
                Modifier.padding(10.dp),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFCCCCCC),
                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            LazyRow {
                items(itemList.filter { it.department=="Computer Science"&& it.year==3 }) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
        }
        item {
            Text(
                text = "BE ",
                Modifier.padding(10.dp),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFCCCCCC),
                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            LazyRow {
                items(itemList.filter { it.department=="Computer Science"&& it.year==4 }) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
        }
        item {
            Text(
                text = "Information Technology",
                Modifier.padding(10.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color =colorResource(id = R.color.LightBackgroundColor),

                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            Text(
                text = "SE ",
                Modifier.padding(10.dp),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFCCCCCC),
                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            LazyRow {
                items(itemList.filter { it.department=="Information Technology"&& it.year==2 }) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
          }
        item {
            Text(
                text = "TE ",
                Modifier.padding(10.dp),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFCCCCCC),
                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            LazyRow {
                items(itemList.filter { it.department=="Information Technology"&& it.year==3 }) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
        }
        item {
            Text(
                text = "BE ",
                Modifier.padding(10.dp),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFCCCCCC),
                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            LazyRow {
                items(itemList.filter { it.department=="Information Technology"&& it.year==4 }) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
        }
        item {
            Text(
                text = "Electronics And Telecommunication",
                Modifier.padding(10.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color =colorResource(id = R.color.LightBackgroundColor),


                    )
            )
        }
        item {
            LazyRow {
                items(itemList) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
        }
        item {
            Text(
                text = "Comming Soon........",
                Modifier.padding(10.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color =colorResource(id = R.color.LightBackgroundColor),

                    textAlign = TextAlign.Center,
                )
            )
        }
        }
    }


