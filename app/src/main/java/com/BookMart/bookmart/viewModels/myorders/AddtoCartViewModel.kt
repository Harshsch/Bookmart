package com.BookMart.bookmart.viewModels.myorders

import androidx.lifecycle.ViewModel
import com.BookMart.bookmart.domain.models.myorders.CartItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CartViewModel : ViewModel() {
    val userId = FirebaseAuth.getInstance().currentUser?.uid // Get the user's ID

    val database = FirebaseDatabase.getInstance()
    val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/cartlist")
    private val cartItems = mutableListOf<CartItem>()
    val newChildRef: DatabaseReference = databaseReference.push()
    // Function to add an item to the cart
    fun addItemToCart(item: CartItem) {
        println("i Am in cart Befor adding  :$cartItems")
        cartItems.add(item)
        println("i Am in cart aFTER ADDING  :$cartItems")
        newChildRef.setValue(item)
    }
    var cartkey=newChildRef.getKey()
}