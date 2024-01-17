package com.BookMart.bookmart.views.SearchView

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.utils.composables.SuggestionCard
import com.BookMart.bookmart.viewModels.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSuggestion(navController: NavController)
{

    var text by remember { mutableStateOf("") }
    val textState = rememberUpdatedState(text)
    val viewmodel: HomeViewModel = viewModel()
    Column(modifier = Modifier.height(800.dp)){
        Row {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            TextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.dim_20))
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.onPrimary),
                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "Search"
                        )
                    }
                },
                trailingIcon = {
                    if(text != "")
                    {
                        Icon(painter = painterResource(id = R.drawable.baseline_close_24),
                            contentDescription ="",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.clickable { text="" })
                    }

                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {

                        viewmodel.searchText=text
                       // navController.navigate("Search_View_rout")
                    }
                )
            )
        }
        val results = viewmodel.searchBooks(text)
        if(text!=""){
            LazyColumn(content = {

                items(results) { item ->
                    SuggestionCard(
                        navController = navController,
                        item = item
                    )
                }
            })
        }

    }
}
