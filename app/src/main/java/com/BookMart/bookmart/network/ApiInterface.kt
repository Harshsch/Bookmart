package com.harsh.booksapi.Interface

import com.harsh.booksapi.model.Booksitem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{
    @GET("Books.json")
    fun getData():Call<Booksitem>
}