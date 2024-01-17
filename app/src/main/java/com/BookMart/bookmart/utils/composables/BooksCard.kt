package com.BookMart.bookmart.utils.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.domain.models.products.ListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksCard(navController: NavController, item: ListItem) {
    Column (modifier = Modifier.padding(4.dp)){
        Card(
            onClick = {
                navController.navigate("BookDisplay/${item.id}")
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(dimensionResource(id = R.dimen.dim_5))
                .shadow(
                    elevation = dimensionResource(id = R.dimen.dim_14),
                    spotColor = Color.Gray,
                    ambientColor = Color.Gray
                )
                .width(210.dp)
                .height(380.dp),
                colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
        ) {
            Image(
                painter = painterResource(id =item.imageResId),
                contentDescription = "image description",
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
                    .shadow(
                        elevation = dimensionResource(id = R.dimen.dim_14),
                        spotColor = Color(0x0D06070D),
                        ambientColor = Color(0x0D06070D)
                    )
                    .width(dimensionResource(id = R.dimen.dim_180))
                    .height(249.60001.dp),
                contentScale = ContentScale.FillBounds
            )
            Column(modifier = Modifier.padding(10.dp)){
                Text(
                    text = item.name,
                    modifier = Modifier.align(Alignment.Start),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.fon_16).value.sp,
                        fontWeight = FontWeight(600),
                        color = MaterialTheme.colorScheme.onPrimary,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Year ${item.year}, Semester ${item.semester}",
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.dim_155))
                        .height(dimensionResource(id = R.dimen.dim_18))
                        .align(Alignment.Start),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.fon_12).value.sp,
                        fontWeight = FontWeight(500),
                        color = MaterialTheme.colorScheme.onPrimary,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Department: ${item.department}",
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.dim_155))
                        .height(dimensionResource(id = R.dimen.dim_18))
                        .align(Alignment.Start),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.fon_12).value.sp,
                        fontWeight = FontWeight(500),
                        color = MaterialTheme.colorScheme.onPrimary,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Price: ${item.price}",
                    modifier = Modifier
                        .width(dimensionResource(id = R.dimen.dim_155))
                        .height(dimensionResource(id = R.dimen.dim_18))
                        .align(Alignment.Start),
                    style = TextStyle(
                        fontSize = dimensionResource(id = R.dimen.fon_12).value.sp,
                        fontWeight = FontWeight(500),
                        color = MaterialTheme.colorScheme.onPrimary,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
