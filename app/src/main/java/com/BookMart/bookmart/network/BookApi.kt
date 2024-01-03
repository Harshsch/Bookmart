package com.BookMart.bookmart.network

import com.BookMart.bookmart.domain.models.products.ListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET("Books.json")
    suspend fun getAllBooks():List<ListItem>

    @GET("Books.json?orderBy=\"id\"")
    suspend fun getSingleBookById(@Query("equalTo")id:Int?):Map<String,ListItem>

}