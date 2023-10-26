package com.BookMart.bookmart

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.BookMart.bookmart.AllScreens.HomeScreen.MyOrdersScreen.MyOrdersScreen
import com.BookMart.bookmart.AllScreens.HomeScreen.SettingScreen.SettingsScreen
import com.BookMart.bookmart.ui.theme.DarkSurfaceColor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

data class BottomNavigationItem(
    val id: Int,
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun BottomBarScreen(
    navController: NavHostController,
    screenContent: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        3
    }
    val routs = listOf(
        "home_route",
        "my_orders_route",
        "settings_route",
    )
    val currentUserinfo = FirebaseAuth.getInstance().currentUser

    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val reference = FirebaseDatabase.getInstance().getReference("users/$userId/cartlist")
    var count by remember { mutableIntStateOf(0) }
    reference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val numberOfElements = snapshot.childrenCount
            count = numberOfElements.toInt()
        }

        override fun onCancelled(error: DatabaseError) {
            println("Database Error: $error")
        }
    })
    val items = listOf(
        BottomNavigationItem(
            id = 0,
            title = "Home",
            selectedIcon = painterResource(id = R.drawable.baseline_home_24),
            unselectedIcon = painterResource(id = R.drawable.outline_home_24),
            hasNews = false,
        ),
        BottomNavigationItem(
            id = 1,
            title = "MyOrders",
            selectedIcon = painterResource(id = R.drawable.baseline_email_24),
            unselectedIcon = painterResource(id = R.drawable.outline_email_24),
            hasNews = false,
            badgeCount = count
        ),
        BottomNavigationItem(
            id = 2,
            title = "Settings",
            selectedIcon = painterResource(id = R.drawable.baseline_settings_24),
            unselectedIcon = painterResource(id = R.drawable.outline_settings_24),
            hasNews = true,
        ),
    )
    var BottomselectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collect { page ->
                BottomselectedItemIndex = page
            }
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        val destinationIndex = routs.indexOf(destination.route)
        if (destinationIndex >= 0) {
            BottomselectedItemIndex = destinationIndex
        }
    }
    // Initialize Firebase Auth

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(

            topBar = {
                TopAppBar(
                    title = {
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                text = "Howdy ${currentUserinfo?.displayName}  !!",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )

                        }
                    },
                    modifier = Modifier,

                    actions = {
                        IconButton(onClick =
                        {
                            if (currentUserinfo != null) {
                                navController.navigate("User_Profile")
                            } else {
                                navController.navigate("Signup")
                            }
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                                contentDescription = "User Profile",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = DarkSurfaceColor,

                        )
                )
            },
            bottomBar = {
                NavigationBar(
                    modifier = Modifier,
                    //.background(DarkPrimaryColor),
                    containerColor = DarkSurfaceColor,
                    //contentColor= Color.Cyan,
                ) {
                    items.forEachIndexed { index, item ->
                        val isSelected = BottomselectedItemIndex == index
                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                if (currentUserinfo != null) {
                                    coroutineScope.launch {
                                        pagerState.scrollToPage(index)
                                    }
                                } else {
                                    navController.navigate("Signup")
                                }
                                BottomselectedItemIndex = index

                            },
                            label = {
                                Text(
                                    text = item.title,
                                    color = Color.White
                                )
                            },
                            alwaysShowLabel = false,
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    if (isSelected) {
                                        Icon(
                                            painter = item.selectedIcon,
                                            contentDescription = item.title,
                                            tint = Color.Black
                                        )


                                    } else {
                                        Icon(
                                            painter = item.unselectedIcon,
                                            contentDescription = item.title,
                                            tint = Color.White
                                        )

                                    }

                                }
                            },
                        )
                    }
                }
            },

            ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {

                HorizontalPager(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = pagerState
                ) { page ->

                        when (page) {
                            0 -> HomeScreen(navController = navController)
                            1 ->  if (currentUserinfo != null ) {MyOrdersScreen(navController = navController)}
                            else {
                                navController.navigate("Signup")
                            }
                            2 ->  if (currentUserinfo != null) {SettingsScreen(navController = navController)}
                            else {
                                navController.navigate("Signup")
                            }
                        }

                    }
                }
            }
        }
    }













