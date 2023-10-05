package com.example.bookmart.AllScreens.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.bookmart.BooksRow
import com.example.bookmart.HeadingTextComponent
import com.example.bookmart.ListItem
import com.example.bookmart.NormalTextComponent
import com.example.bookmart.R
import com.example.bookmart.itemList
import com.google.firebase.auth.FirebaseAuth


@Composable
fun HomeScreen(navController: NavHostController)
{

    val currentUser = FirebaseAuth.getInstance().currentUser

    Column(
        Modifier
            .padding(0.dp, 20.dp, 0.dp, 80.dp)
            .background(color = colorResource(id = R.color.DarkSurfaceColor))
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 50.dp, 16.dp, 16.dp),
            
            )
        {
            Column(modifier = Modifier.padding(20.dp, 20.dp, 20.dp, 0.dp)) {
                if (currentUser != null) {
                    // User is signed in, display the welcome message
                    NormalTextComponent(value = "Welcome back ${currentUser.displayName}")
                } else {
                    // User is not signed in
                    NormalTextComponent(value = "Welcome")
                }
            }
            Column(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp)) {
                HeadingTextComponent(value =stringResource(id =R.string.ready_for_exam) )
            }

        }

//        Row(modifier = Modifier.padding(5.dp)) {
//            Card(
//                elevation = CardDefaults.cardElevation(
//                    defaultElevation = 10.dp
//                ),
//                modifier = Modifier.weight(1f),
//               // elevation = 8.dp,
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                ExposedDropdownMenu(
//                    items = listOf("Department 1", "Department 2", "Department 3"),
//                    selectedValue = selectedOption1,
//                    onValueSelected = { selectedOption1 = it }
//                )
//            }
//
//            Card(
//                elevation = CardDefaults.cardElevation(
//                    defaultElevation = 10.dp
//                ),
//                modifier = Modifier.weight(1f),
//                //elevation = 8.dp,
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                ExposedDropdownMenu(
//                    items = listOf("SE", "TE", "BE"),
//                    selectedValue = selectedOption2,
//                    onValueSelected = { selectedOption2 = it }
//                )
//            }
//
//            Card(
//                elevation = CardDefaults.cardElevation(
//                    defaultElevation = 10.dp
//                ),
//                modifier = Modifier.weight(1f),
//                //elevation = 8.dp,
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                ExposedDropdownMenu(
//                    items = listOf("SEM-1", "SEM-2"),
//                    selectedValue = selectedOption3,
//                    onValueSelected = { selectedOption3 = it }
//                )
//            }
//        }



        DepartmentBooks(navController = navController)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentBooks(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),

        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = "Search"
                )
            }
        },
//        trailingIcon = {
//            IconButton(onClick = { /*TODO*/ }) {
//                Image(
//                    painter = painterResource(id = R.drawable.baseline_mic_24),
//                    contentDescription = "Search"
//                )
//            }
//
//        },
    )
    val searchQuery = text
    fun searchBooksByName(query: String): List<ListItem> {
        val lowercaseQuery = query.toLowerCase()
        return itemList.filter { it.name.toLowerCase().contains(lowercaseQuery) }
    }

    val results = searchBooksByName(searchQuery)



    LazyColumn()
    {
        item {
            Text(
                text = "Search Results",
                Modifier.padding(10.dp),

                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF19191B),
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
                    color = Color(0xFF19191B),
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
                    color = Color(0xFF19191B),
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
                items(itemList.filter { it.year ==2}) { item ->
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
                items(itemList.filter { it.year ==3}) { item ->
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
                items(itemList.filter { it.year ==4}) { item ->
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
                    color = Color(0xFF19191B),
                    textAlign = TextAlign.Center,
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
                text = "Electronics And Telecommunication",
                Modifier.padding(10.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF19191B),

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
                    color = Color(0xFF19191B),
                    textAlign = TextAlign.Center,
                )
            )
        }
        }
    }

