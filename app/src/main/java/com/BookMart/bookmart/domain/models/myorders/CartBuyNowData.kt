package com.BookMart.bookmart.domain.models.myorders

data class CartBuyNowData(
    val name: String,
    val streetAddress: String,
    val city: String,
    val cartItems: List<CartItem>,

    )