package com.BookMart.bookmart.domain.models.myorders

import com.BookMart.bookmart.R

data class CartItem(
    val id:Int =0,
    val name: String = "",
    val price: Int = 0,
    val quantity:Int=1,
    val imageResId: Int= R.drawable.cse_img_13,
)