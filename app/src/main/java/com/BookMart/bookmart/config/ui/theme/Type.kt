package com.BookMart.bookmart.config.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = dimensionResource(id = R.dimen.fon_16).value.sp,
        lineHeight = dimensionResource(id = R.dimen.fon_24).value.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = dimensionResource(id = R.dimen.fon_22).value.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = dimensionResource(id = R.dimen.fon_11).value.sp,
        lineHeight = dimensionResource(id = R.dimen.fon_16).value.sp,
        letterSpacing = 0.5.sp
    )
    */
)