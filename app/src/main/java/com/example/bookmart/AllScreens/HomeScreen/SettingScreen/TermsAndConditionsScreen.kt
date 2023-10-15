package com.example.bookmart

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TermsAndConditionsScreen() {
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


