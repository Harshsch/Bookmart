package com.BookMart.bookmart.repositories

import com.harsh.booksapi.Interface.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://book-mart-6613c-default-rtdb.firebaseio.com/") // Replace with your API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)
}
