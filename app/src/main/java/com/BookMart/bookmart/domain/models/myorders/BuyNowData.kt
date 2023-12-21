package com.BookMart.bookmart.domain.models.myorders

data class BuyNowData(
    val name: String,
    val streetAddress: String,
    val city: String,
    val mobilenumber:String,
    val productname: String,
    val priceperunit: Int,
    val quantity: Int,
    val totalprice: String

)