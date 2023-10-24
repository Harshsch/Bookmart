package com.BookMart.bookmart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.BookMart.bookmart.AllScreens.HomeScreen.MyOrdersScreen.MyOrdersScreen
import com.BookMart.bookmart.AllScreens.HomeScreen.SettingScreen.SettingsScreen
import com.BookMart.bookmart.ui.theme.DarkPrimaryColor
import com.BookMart.bookmart.ui.theme.LightSecondaryColor
import com.BookMart.bookmart.ui.theme.LightSurfaceColor

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
                        tint = Color.Black,
                        modifier = Modifier.clickable(onClick = { navController.navigate("home_route") }
                        ))
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.DarkPrimaryColor),

                    )
            )
        },)
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            screenContent()
        }

    }
}