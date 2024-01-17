package com.BookMart.bookmart.viewModels.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.BookMart.bookmart.data.itemList
import com.BookMart.bookmart.domain.models.products.ListItem

class HomeViewModel : ViewModel() {
    fun searchBooks(query: String): List<ListItem> {
        val lowercaseQuery = query.toLowerCase()
        return itemList.filter { it.name.toLowerCase().contains(lowercaseQuery) }
    }
    var searchText by mutableStateOf("")
}

