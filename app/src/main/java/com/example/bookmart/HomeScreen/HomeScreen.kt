package com.example.bookmart.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.bookmart.NormalTextComponent
import com.example.bookmart.R
import com.example.bookmart.itemList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController)
{
    var text by remember { mutableStateOf("") }
    Column(
        Modifier
            .width(428.dp)
            .height(926.dp)
            .padding(20.dp, 20.dp, 20.dp, 80.dp)
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
                NormalTextComponent(value = stringResource(id =R.string.welcome))
            }
            Column(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 20.dp)) {
                HeadingTextComponent(value =stringResource(id =R.string.ready_for_exam) )
            }
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
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_mic_24),
                            contentDescription = "Search"
                        )
                    }

                },
            )
        }
        Departments(navController = navController)
        DepartmentBooks(navController = navController)
    }
}
@Composable
fun Departments(navController: NavHostController)
{
    val itemList = listOf(
        "CSE",
        "E&TC",
        "Mech",
        "CE",
        "EE",
        "IT",
        "ICE",
        "ChemE",
        "Biotech",
        "Aero",
        "Auto",
        "EnvE",
        "MatSci",
        "ProdE",
        "Marine"
    )
    Column {
        var selectedIndex by remember { mutableStateOf(-1) }

        LazyRow() {
            itemsIndexed(itemList) { index, item ->
                val isSelected = selectedIndex == index
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Color.DarkGray else MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    modifier = Modifier
                        .padding(5.dp)
                        .padding(5.dp)
                        .size(50.dp, 30.dp)
                        .clickable {
                            selectedIndex = if (isSelected) -1 else index
                        },
                ){
                    Text(
                        text = item,
                        modifier = Modifier
                            .padding(4.dp)
                            .align(Alignment.CenterHorizontally),
                        color = if (isSelected) Color.White else Color.Black
                    )
                }
            }
        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentBooks(navController: NavHostController) {
    LazyColumn()
    {
        item {
            LazyRow() {
                items(itemList) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
        }
        item {
            Text(
                text = "New Arrivals",
                Modifier
                    .width(153.dp)
                    .height(36.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF19191B),
                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            LazyRow() {
                items(itemList) { item ->
                    BooksRow(navController = navController, item = item)
                }
            }
          }
        }
    }

