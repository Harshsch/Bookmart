package com.example.bookmart


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


data class BottomNavigationItem(
    val id: Int,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarScreen(
    navController: NavController,
    screenContent: @Composable () -> Unit
) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    val database = FirebaseDatabase.getInstance()
    val reference = FirebaseDatabase.getInstance().getReference("users/$userId/cartlist")
    var count by remember { mutableStateOf(0) }
    reference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val numberOfElements = snapshot.childrenCount
            if (numberOfElements != null) {
                 count= numberOfElements.toInt()
            }
        }
        override fun onCancelled(error: DatabaseError) {
            println("Database Error: $error")
        }
    })
    var isDrawerOpen by remember { mutableStateOf(false) }
    val items = listOf(
        BottomNavigationItem(
            id = 0,
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            id = 1,
            title = "MyOrders",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            hasNews = false,
            badgeCount = count
        ),
        BottomNavigationItem(
            id = 2,
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true,
        ),
    )
    var BottomselectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        BottomselectedItemIndex = when (destination.route) {
            "home_route" -> 0
            "my_orders_route" -> 1
            "settings_route" -> 2
            else -> -1 // Define a fallback index for unmatched routes
        }
    }
    lateinit var auth: FirebaseAuth
    // Initialize Firebase Auth
    auth = Firebase.auth
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var TopselectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }

            Scaffold(

                topBar = {

                    TopAppBar(



                        title = {
                            Text(
                                "",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        modifier = Modifier
                            //.background(colorResource(id = R.color.DarkBackgroundColor))
                            .clip(RoundedCornerShape(20.dp)),
                              actions = {
                            IconButton(onClick = { navController.navigate("User_Profile") }) {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "User Profile"
                                )
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor =colorResource(id = R.color.DarkPrimaryColor),

                            )

                       )
                },
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier
                            //.background(colorResource(id = R.color.DarkBackgroundColor))
                            .clip(RoundedCornerShape(20.dp)),
                        containerColor= colorResource(id = R.color.DarkPrimaryColor),
                        contentColor= Color.Cyan,
                    ) {
                        items.forEachIndexed { index, item ->
                            val isSelected = BottomselectedItemIndex == index
                            NavigationBarItem(
                                selected = isSelected,
                                onClick = {
                                    val currentUser = auth.currentUser
                                    BottomselectedItemIndex = index
                                    val destination = when (item.title) {
                                        "Home" -> "home_route"
                                        "MyOrders" -> "my_orders_route"
                                        "Settings" -> "settings_route"
                                        else -> "fallback_route" // Define a fallback route for unmatched titles
                                    }
                                    if (currentUser != null&&destination!="Home") {
                                        navController.navigate(destination)
                                    }
                                    else if(destination=="Home")
                                        {
                                            navController.navigate(destination)
                                            {popUpTo("home_route") { inclusive = true }}

                                        }

                                    else {
                                        navController.navigate("Signup")
                                    }
                                },
                                label = {
                                    Text(text = item.title)
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
                                        Icon(
                                            imageVector = if (isSelected) {
                                                item.selectedIcon

                                            } else {
                                                item.unselectedIcon
                                            },
                                            contentDescription = item.title,
                                        )
                                    }
                                }
                            )
                        }
                    }
                },
                //backgroundColor =  Color(0xFF0d0e1c), // Change the background color here

            ) {

                screenContent()

            }

    }
}






