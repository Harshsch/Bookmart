package com.BookMart.bookmart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.BookMart.bookmart.config.ui.theme.DarkSurfaceColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topbackbar(navController: NavHostController,screenContent: @Composable () -> Unit)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("",
                    color = Color.White,
                    fontWeight = FontWeight.Bold)
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.clickable(onClick = {
                            navController.popBackStack()
//
                        }
                        ))
                },

                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = DarkSurfaceColor,

                    )
            )
        },)
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dim_16)),
        ) {
            screenContent()
        }

    }
}