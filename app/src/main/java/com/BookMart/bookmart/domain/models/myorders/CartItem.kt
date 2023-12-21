package com.BookMart.bookmart.domain.models.myorders

data class CartItem(
    val id:Int =0,
    val name: String = "",
    val price: Int = 0,
    val quantity:Int=1,
    val imageResId: Int=0,
)