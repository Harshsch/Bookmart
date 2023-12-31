package com.BookMart.bookmart.views.setting
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.BookMart.bookmart.R
import com.BookMart.bookmart.utils.composables.ConfirmDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun SettingsScreen(navController: NavHostController) {
    var showDialog by remember {
        mutableStateOf (false)
    }
    val context = LocalContext.current

        Surface(
            color = MaterialTheme.colorScheme.background
        ) { LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.dim_16))


            ){
            item {

                Text(
                    text = "Hey! BookMart Customer",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                        color =MaterialTheme.colorScheme.onPrimary,
                    ),           modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dim_16)),

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
                        .padding(vertical = dimensionResource(id = R.dimen.dim_16))
                )
            }


            item {
                Divider(
                    color = Color.Gray,
                    thickness = 0.5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(id = R.dimen.dim_16))
                )
            }

            item {
                Text(
                    text = "Feedback & Information",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
                        color =MaterialTheme.colorScheme.onPrimary,
                    ),  modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dim_16)),

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
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dim_32)))
            }
            item{
        Button(
            onClick = {  val firebaseAuth = FirebaseAuth.getInstance()

                firebaseAuth.signOut()

                val authStateListener = FirebaseAuth.AuthStateListener {
                    if (it.currentUser == null) {
                        Log.d(TAG, "inside signout success")
                        Toast.makeText(context, "Signedout successfully", Toast.LENGTH_SHORT).show()


                    } else {
                        Log.d(TAG, "signoutnot complete")
                    }

                }
                firebaseAuth.addAuthStateListener(authStateListener)
                navController.navigate("home_route")
            },
        ) {
            Text(text = "Logout")
        }}
            item{
                    Button(
                        onClick = {
                            showDialog = true
                        },
                    ) {
                        Text(text = "Delete account")
                    }

        }
            }
            if (showDialog) {
                ConfirmDialog(
                    onConfirm = {
                        val user = Firebase.auth.currentUser!!

                        user.delete()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d(TAG, "User account deleted.")
                                }
                            }
                        navController.navigate("home_route")

                        showDialog = false
                    },
                    onCancel = {
                        // Handle cancellation logic
                        showDialog = false
                    }
                )
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
            .padding(vertical = dimensionResource(id = R.dimen.dim_12))
            //.background(colorResource(id = R.color.DarkSecondaryColor))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.dim_24)),
            tint =MaterialTheme.colorScheme.onPrimary,

            )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dim_16)))
        Text(text = title,style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = dimensionResource(id = R.dimen.fon_24).value.sp,
            color =MaterialTheme.colorScheme.onPrimary,
        ),)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.baseline_chevron_right_24),
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.dim_24)),
            tint =MaterialTheme.colorScheme.onPrimary,

            )
    }
}
@Composable
fun TermsAndCondition() {
    val scrollState = rememberScrollState()

    ElevatedCard(
        //elevation = CardDefaults.elevation(5.dp),
        modifier = Modifier
            .wrapContentSize()
            .padding(dimensionResource(id = R.dimen.dim_16))
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
fun FAQ() {
    val scrollState = rememberScrollState()

    ElevatedCard(
        modifier = Modifier
            .wrapContentSize()
            .padding(dimensionResource(id = R.dimen.dim_16))
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
