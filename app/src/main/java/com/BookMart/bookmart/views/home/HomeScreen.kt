package com.BookMart.bookmart
//fseefsgfsfe
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
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
import coil.compose.AsyncImage
import com.BookMart.bookmart.data.itemList
import com.BookMart.bookmart.domain.models.products.ListItem
import com.BookMart.bookmart.utils.composables.PageIndicator
import com.BookMart.bookmart.utils.composables.ShimmerBrush
import com.BookMart.bookmart.viewModels.home.HomeViewModel
import com.BookMart.bookmart.viewModels.home.MyViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.harsh.booksapi.model.Booksitem
import com.harsh.booksapi.model.BooksitemItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
//hello

@OptIn(ExperimentalFoundationApi::class)
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


            val images = listOf(
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3,
                R.drawable.banner4
            )
            val pagerState = rememberPagerState (
                pageCount = {images.size}
            )
            LaunchedEffect(Unit) {
                while (true) {
                    delay(2000)
                    val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                    pagerState.scrollToPage(nextPage)
                }
            }
            val scope = rememberCoroutineScope()

            Column(
                Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.wrapContentSize()
                    //.align(Alignment.CenterHorizontally)
                ) {
                    HorizontalPager(
                        state = pagerState,
                        Modifier
                            .wrapContentSize()

                    ) { currentPage ->

                        Card(
                            Modifier
                                .height(250.dp)
                                .fillMaxWidth()
                                .padding(26.dp),

                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = images[currentPage]),
                                contentDescription = "",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            val nextPage = pagerState.currentPage + 1
                            if (nextPage < images.size) {
                                scope.launch {
                                    pagerState.scrollToPage(nextPage)
                                }
                            }
                        },
                        Modifier
                            .padding(30.dp)
                            .size(48.dp)
                            .align(Alignment.CenterEnd)
                            .clip(CircleShape),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color(0x52373737)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight, contentDescription = "",
                            Modifier.fillMaxSize(),
                            tint = Color.LightGray
                        )
                    }
                    IconButton(
                        onClick = {
                            val prevPage = pagerState.currentPage -1
                            if (prevPage >= 0) {
                                scope.launch {
                                    pagerState.scrollToPage(prevPage)
                                }
                            }
                        },
                        Modifier
                            .padding(30.dp)
                            .size(48.dp)
                            .align(Alignment.CenterStart)
                            .clip(CircleShape),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color(0x52373737)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "",
                            Modifier.fillMaxSize(),
                            tint = Color.LightGray
                        )
                    }
                }

                PageIndicator(
                    pageCount = images.size,
                    currentPage = pagerState.currentPage,

                )

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


        item{mainBooksRow()}
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

@Composable
fun mainBooksRow(viewModel: MyViewModel= viewModel()){
    val bookList by remember { viewModel.data }.observeAsState()



    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    bookList?.let { books ->
        BookList(books)
    }
 }

@Composable
fun BookList(books: Booksitem) {
    LazyRow{
        items(books) { book ->
            BookListItem(book)
        }
    }
}
@Composable
fun BookListItem(book: BooksitemItem, brush: Brush = ShimmerBrush()) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = Color.White)
            .clip(RoundedCornerShape(8.dp))
            .shadow(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(modifier = Modifier.background(
                SolidColor(Color.Transparent)
            )){

                AsyncImage(
                    model = book.imageResId,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Transparent)
                )
            }

            // Book details column
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            ) {
                // Book name
                Text(
                    text = book.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(bottom = 4.dp),
                    color = Color.Black
                )

                // Book department
                Text(
                    text = book.department,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Book price
                Text(
                    text = "₹${book.price}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}
