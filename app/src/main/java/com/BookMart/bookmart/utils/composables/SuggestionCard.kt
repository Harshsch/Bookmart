package com.BookMart.bookmart.utils.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.domain.models.products.ListItem
import com.BookMart.bookmart.viewModels.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestionCard(
    navController: NavController,
    item:ListItem)
{
    val viewmodel: HomeViewModel = viewModel()
    Row(modifier = Modifier.clickable { viewmodel.searchText=item.name
    navController.navigate("Search_View_rout")
    }) {

        Icon(painter = painterResource(id = R.drawable.baseline_search_24) ,
            contentDescription = "",
            )
        Column{
            Text(
                text = item.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            )
            Text(
                text = item.department,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Blue
                )
            )
        }

    }

}