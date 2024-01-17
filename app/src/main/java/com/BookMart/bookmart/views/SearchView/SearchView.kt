package com.BookMart.bookmart.views.SearchView

import android.widget.SearchView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.utils.composables.BooksCard
import com.BookMart.bookmart.viewModels.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(navController: NavController)
{
    BackHandler {
        navController.popBackStack()
    }
    val viewmodel: HomeViewModel = viewModel()
//    if(viewmodel.searchText =="")
//    {SearchSuggestion( navController)}
//    else{
        var text by remember { mutableStateOf(viewmodel.searchText) }
        Column {
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.dim_20))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.dim_30))),
                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "Search"
                        )
                    }
                },
            )
            val searchQuery = text

            val results = viewmodel.searchBooks(searchQuery)

            LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                items(results) { item ->
                    BooksCard(navController = navController, item = item)
                }
            })
        }
    }
