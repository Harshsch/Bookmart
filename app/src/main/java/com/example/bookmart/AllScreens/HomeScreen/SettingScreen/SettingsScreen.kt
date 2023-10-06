package com.example.bookmart.AllScreens.HomeScreen.SettingScreen
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookmart.R
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SettingsScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current


    val darkBackgroundColor = Color(0xFF0d0e1c)
    val textureColor = Color(0xFF6a6f9a) // Define your texture color

    val brush = Brush.horizontalGradient(
        colors = listOf( textureColor ,textureColor),
    )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()


                .background(brush=brush),


        ) {

            item {
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "Hey! BookMart Customer",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color =colorResource(id = R.color.LightBackgroundColor),
                    ),           modifier = Modifier.padding(bottom = 16.dp),

                    )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Person,
                    title = "Edit Profile",
                    onClick = { navController.navigate("User_Profile")/* Handle Edit Profile click */ }
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Place,
                    title = "Saved Addresses",
                    onClick = { navController.navigate("SavedAddress")/* Handle Saved Addresses click */ }
                )
            }

            item {
                Divider(
                    color = Color.Gray,
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }


            item {
                Divider(
                    color = Color.Gray,
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }

            item {
                Text(
                    text = "Feedback & Information",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color =colorResource(id = R.color.LightBackgroundColor),
                    ),  modifier = Modifier.padding(bottom = 16.dp),

                    )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.Policy,
                    title = "Terms, Policies and Licenses",
                    onClick = { navController.navigate("Terms") }
                )
            }

            item {
                SettingsItem(
                    icon = Icons.Default.QuestionAnswer,
                    title = "Browse FAQs",
                    onClick = {navController.navigate("FAQ")}
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Button(
                    onClick = {  val firebaseAuth = FirebaseAuth.getInstance()

                        firebaseAuth.signOut()

                        val authStateListener = FirebaseAuth.AuthStateListener {
                            if (it.currentUser == null) {
                                Log.d(TAG, "inside signout success")

                            } else {
                                Log.d(TAG, "signoutnot complete")

                            }

                        }
                        firebaseAuth.addAuthStateListener(authStateListener)
                        navController.navigate("home_route")
                    }
                ) {
                    Text(text = "Logout")
                }
            }
        }

}

@Composable
fun SettingsItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint =colorResource(id = R.color.LightBackgroundColor),

            )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title,style = TextStyle(
            fontWeight = FontWeight.Normal

            ,
            fontSize = 24.sp,
            color =colorResource(id = R.color.LightBackgroundColor),
        ),)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.baseline_chevron_right_24),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint =colorResource(id = R.color.LightBackgroundColor),

            )
    }
}
@Composable
fun TermsAndCondition(navController: NavController) {
    val scrollState = rememberScrollState()

    ElevatedCard(
        //elevation = CardDefaults.elevation(5.dp),
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp,80.dp,16.dp,80.dp)
            .verticalScroll(state = scrollState)
    )
    {

        Text(
            text =
                    "Terms of Service\n" +
                    "\n" +
                    "Welcome to BookMart! These Terms of Service govern your use of our website and mobile app. By accessing or using our services, you agree to abide by these terms.\n" +
                    "\n" +
                    "1. User Registration\n" +
                    "\n" +
                    "- You must be at least 18 years old to register for an account on BookMart.\n" +
                    "- You are responsible for maintaining the confidentiality of your account information.\n" +
                    "\n" +
                    "2. Content\n" +
                    "\n" +
                    "- You may use BookMart to view and purchase books.\n" +
                    "- You may not upload, share, or distribute content that violates intellectual property rights, is offensive, or is illegal.\n" +
                    "\n" +
                    "3. Purchases\n" +
                    "\n" +
                    "- Book prices are subject to change without notice.\n" +
                    "- We reserve the right to refuse or cancel any order for any reason.\n" +
                    "- Payment information is handled securely by our payment processor.\n" +
                    "\n" +
                    "4. Privacy\n" +
                    "\n" +
                    "- We collect and use your personal information in accordance with our Privacy Policy.\n" +
                    "- By using BookMart, you consent to the collection and use of your information as described in our Privacy Policy.\n" +
                    "\n" +
                    "5. Intellectual Property\n" +
                    "\n" +
                    "- BookMart and its content are protected by copyright and other intellectual property laws.\n" +
                    "- You may not use, copy, or distribute our content without permission.\n" +
                    "\n" +
                    "6. Termination\n" +
                    "\n" +
                    "- We may terminate or suspend your account for violating these Terms of Service.\n" +
                    "- You may terminate your account at any time by contacting us.\n" +
                    "\n" +
                    "7. Disclaimer\n" +
                    "\n" +
                    "- We provide BookMart on an \"as-is\" basis and make no warranties or representations regarding its accuracy or reliability.\n" +
                    "- We are not responsible for any damages or losses resulting from your use of BookMart.\n" +
                    "\n" +
                    "8. Changes to Terms\n" +
                    "\n" +
                    "- We may update these Terms of Service from time to time. Check this page for the latest version.\n" +
                    "\n" +
                    "Privacy Policy\n" +
                    "\n" +
                    "1. Information Collection\n" +
                    "\n" +
                    "- We collect information you provide when registering, making purchases, or interacting with BookMart.\n" +
                    "- We use cookies and similar technologies to collect data about your usage of BookMart.\n" +
                    "\n" +
                    "2. Information Use\n" +
                    "\n" +
                    "- We use your information to process orders, personalize your experience, and improve BookMart.\n" +
                    "- We may share your information with trusted third-party partners for specific purposes.\n" +
                    "\n" +
                    "3. Security\n" +
                    "\n" +
                    "- We take reasonable steps to protect your information, but no method of transmission is entirely secure.\n" +
                    "- You are responsible for maintaining the security of your account.\n" +
                    "\n" +
                    "4. Changes to Privacy Policy\n" +
                    "\n" +
                    "- We may update our Privacy Policy as necessary. Check this page for the latest version.\n" +
                    "\n" +
                    "---\n" +
                    "\n" )
    }
}


@Composable
fun FAQ(navController: NavController) {
    val scrollState = rememberScrollState()

    ElevatedCard(
        //elevation = CardDefaults.elevation(5.dp),
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp,80.dp,16.dp,80.dp)
            .verticalScroll(state = scrollState)
    )
    {

        Text(
            text =
                    "Frequently Asked Questions (FAQs)\n" +
                    "\n" +
                    "1. How do I create an account on BookMart?\n" +
                    "\n" +
                    "Creating an account on BookMart is easy! Simply click the \"Sign Up\" or \"Register\" button on our website or mobile app. You'll need to provide some basic information, including your name and email address. Once you've filled out the required fields, click \"Submit,\" and you'll have your own BookMart account.\n" +
                    "\n" +
                    "2. How do I search for books on BookMart?\n" +
                    "\n" +
                    "To search for books, use the search bar at the top of our website or app. You can enter keywords, book titles, authors, or ISBN numbers to find the books you're looking for. You can also use filters to narrow down your search results by genre, price, and more.\n" +
                    "\n" +
                    "3. How can I place an order?\n" +
                    "\n" +
                    "Once you've found the book you want to purchase, click on it to view the details. On the book's page, you'll see an \"Add to Cart\" or \"Buy Now\" button. Clicking on either of these buttons will guide you through the checkout process. You'll need to provide your shipping address and payment information to complete your order.\n" +
                    "\n" +
                    "4. What payment methods are accepted on BookMart?\n" +
                    "\n" +
                    "BookMart accepts a variety of payment methods, including credit and debit cards, digital wallets, and more. We use a secure payment processing system to protect your financial information.\n" +
                    "\n" +
                    "5. How long does shipping take?\n" +
                    "\n" +
                    "Shipping times can vary depending on your location and the shipping method you choose during checkout. We strive to deliver your books as quickly as possible. You can track the status of your order in your BookMart account.\n" +
                    "\n" +
                    "6. Can I return or exchange books?\n" +
                    "\n" +
                    "Yes, we have a return and exchange policy in place. If you receive a damaged or incorrect book, please contact our customer support team within 7 days of receiving your order. We'll guide you through the return or exchange process.\n" +
                    "\n" +
                    "7. How can I contact BookMart's customer support?\n" +
                    "\n" +
                    "If you have any questions, concerns, or need assistance, you can reach our customer support team through our \"Contact Us\" page on our website or app. We're here to help you with any issues or inquiries.\n" +
                    "\n" +
                    "8. Is my personal information safe with BookMart?\n" +
                    "\n" +
                    "We take your privacy seriously and have implemented security measures to protect your personal information. Please refer to our Privacy Policy for more details on how we handle your data.\n" +
                    "\n" +
                    "9. Can I sell my books on BookMart?\n" +
                    "\n" +
                    "At this time, we do not have a platform for individuals to sell their books directly. Book listings on our platform are managed by authorized sellers and publishers.\n" +
                    "\n" +
                    "10. How do I reset my password if I forget it?\n" +
                    "\n" +
                    "If you forget your password, click on the \"Forgot Password\" link on the login page. You'll receive instructions on how to reset your password via email.\n" +
                    "\n" +
                    "---\n")
                     }
}
