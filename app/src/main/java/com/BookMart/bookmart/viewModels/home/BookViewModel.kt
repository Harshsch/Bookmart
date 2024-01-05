package com.BookMart.bookmart.viewModels.home

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.BookMart.bookmart.repositories.ApiService
import com.harsh.booksapi.model.Booksitem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyViewModel : ViewModel() {
    private val _data = MutableLiveData<Booksitem>()
    val data: LiveData<Booksitem> get() = _data

    fun fetchData() {
        ApiService.apiInterface.getData().enqueue(object : Callback<Booksitem> {
            override fun onResponse(call: Call<Booksitem>, response: Response<Booksitem>) {
                if (response.isSuccessful) {
                    _data.value = response.body()
                }
                else {
                    Log.e("MyViewModel", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Booksitem>, t: Throwable) {
                Log.e("MyViewModel", "Error: ${t.message}")
            }
        })
    }
    }

