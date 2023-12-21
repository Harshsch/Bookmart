package com.BookMart.bookmart.domain.models.products

data class ListItem(
    val id: Int,
    val imageResId: Int,
    val name: String,
    val price: Int,
    val department: String,
    val year: Int,
    val semester: Int,
    val description: String
)