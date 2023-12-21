package com.BookMart.bookmart.domain.models.myorders

data class MyOrders(
    val id:Int=0,
    val name: String = "",
    val payment: String = "",
    val status: String = "",
    val imageResIdorder: Int=0,
    val orderkey:String="",
    val orderlistkey:String="",
)