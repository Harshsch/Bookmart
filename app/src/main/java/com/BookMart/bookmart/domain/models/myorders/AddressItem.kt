package com.BookMart.bookmart.domain.models.myorders

data class Address(
    val id: String = "0",
    val name: String = "",
    val streetAddress: String = "",
    val city: String = "",
    val mobileNumber: String? = null,
)