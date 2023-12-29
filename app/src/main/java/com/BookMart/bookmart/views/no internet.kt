package com.BookMart.bookmart.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.BookMart.bookmart.R

@Composable
fun NoInternetScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.dim_16)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.lost_connection),
            contentDescription = "No Internet GIF",
            modifier = Modifier.size(dimensionResource(id = R.dimen.dim_200))
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_16)))

        Text(
            text = "No Internet Connection",
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_16)))

        Text(
            text = "Please check your internet connection and try again.",
            style = TextStyle(
                fontSize = dimensionResource(id = R.dimen.fon_16).value.sp,
                fontWeight = FontWeight.Normal
            ),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_32)))

        Button(
            onClick = { navController.navigate("home_route") },
            modifier = Modifier.padding(dimensionResource(id = R.dimen.dim_8))
        ) {
            Text(text = "Retry")
        }
    }
}
