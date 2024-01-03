package com.harsh.booksapi.model

data class BooksitemItem(
    val department: String,
    val description: String,
    val id: Int,
    val imageResId: String,
    val name: String,
    val price: Int,
    val semester: Int,
    val year: Int
)