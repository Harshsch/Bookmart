package com.BookMart.bookmart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.BookMart.bookmart.data.itemList
import com.BookMart.bookmart.domain.models.products.ListItem
import com.BookMart.bookmart.viewModels.home.HomeViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavHostController)
{
    val context = LocalContext.current
    FirebaseApp.initializeApp(context)
    FirebaseAuth.getInstance().currentUser

    Surface(color = MaterialTheme.colorScheme.background){
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dim_16))
        ) {
            var isCardVisible by remember { mutableStateOf(true) }

            if (isCardVisible) {
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = dimensionResource(id = R.dimen.dim_10)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.dim_16)),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.DarkSecondaryColor),
                    ),
                ) {
                    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.dim_20))) {
                        Text(
                            text = stringResource(id = R.string.ready_for_exam),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(),
                            style = TextStyle(
                                fontSize = dimensionResource(id = R.dimen.fon_30).value.sp,
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
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentBooks(navController: NavHostController,) {
    val viewmodel:HomeViewModel= viewModel()
    var text by remember { mutableStateOf("") }
    val lazyColumnState = rememberLazyListState()
    val textState = rememberUpdatedState(text)
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_12)))
    TextField(
        value = text,
        onValueChange = {
                newText ->
            text = newText

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.dim_20))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.dim_30)))
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
            lazyColumnState.animateScrollToItem(index = 0)
        }
    }
    val searchQuery = text

    val results = viewmodel.searchBooks(searchQuery)

    LazyColumn(state = lazyColumnState)
    {
        item {
            Text(
                text = "Search Results",
                Modifier.padding(dimensionResource(id = R.dimen.dim_10))
                ,

                 style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color =MaterialTheme.colorScheme.onPrimary,

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
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),

                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color =MaterialTheme.colorScheme.onPrimary,

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
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),

                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color =MaterialTheme.colorScheme.onPrimary,

                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            Text(
                text = "SE",
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color = MaterialTheme.colorScheme.onPrimary,
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
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color = MaterialTheme.colorScheme.onPrimary,
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
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color = MaterialTheme.colorScheme.onPrimary,
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
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color =MaterialTheme.colorScheme.onPrimary,

                    textAlign = TextAlign.Center,
                )
            )
        }
        item {
            Text(
                text = "SE ",
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color = MaterialTheme.colorScheme.onPrimary,
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
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color = MaterialTheme.colorScheme.onPrimary,
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
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),
                textAlign = TextAlign.Center,

                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color = MaterialTheme.colorScheme.onPrimary,
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
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color =MaterialTheme.colorScheme.onPrimary,
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
                Modifier.padding(dimensionResource(id = R.dimen.dim_10)),
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                    fontWeight = FontWeight(600),
                    color =MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                )
            )
        }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksRow(navController: NavHostController, item: ListItem) {
    Column {

        Card(
            onClick = { navController.navigate("BookDisplay/${item.id}")
            },
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dim_5))
                .shadow(
                    elevation = dimensionResource(id = R.dimen.dim_14),
                    spotColor = Color(0x0D06070D),
                    ambientColor = Color(0x0D06070D)
                )
                .width(dimensionResource(id = R.dimen.dim_180))
                .height(290.60001.dp),
            colors = CardDefaults.cardColors(
                containerColor =colorResource(id = R.color.DarkSecondaryColor),
            ),
            ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = "image description",
                modifier = Modifier
                    .shadow(
                        elevation = dimensionResource(id = R.dimen.dim_14),
                        spotColor = Color(0x0D06070D),
                        ambientColor = Color(0x0D06070D)
                    )
                    .width(dimensionResource(id = R.dimen.dim_180))
                    .height(249.60001.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = item.name,
                Modifier,
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_16).value.sp,
                    fontWeight = FontWeight(600),
                    color = colorResource(id = R.color.LightBackgroundColor),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Year ${item.year}, Semester ${item.semester}",
                Modifier
                    .width(dimensionResource(id = R.dimen.dim_155))
                    .height(dimensionResource(id = R.dimen.dim_18))
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_12).value.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFF2F2F2),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Department: ${item.department}",
                Modifier
                    .width(dimensionResource(id = R.dimen.dim_155))
                    .height(dimensionResource(id = R.dimen.dim_18))
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_12).value.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9D9D9D),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = "Price: ${item.price}",
                Modifier
                    .width(dimensionResource(id = R.dimen.dim_155))
                    .height(dimensionResource(id = R.dimen.dim_18))
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(
                    fontSize = dimensionResource(id = R.dimen.fon_12).value.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF9D9D9D),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}


