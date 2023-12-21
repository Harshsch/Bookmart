package com.BookMart.bookmart.viewModels.myorders

import androidx.lifecycle.ViewModel
import com.BookMart.bookmart.data.AddToCart.MyOrders
import com.BookMart.bookmart.domain.models.myorders.MyOrders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MyOrdersViewModel : ViewModel() {
    val userId = FirebaseAuth.getInstance().currentUser?.uid // Get the user's ID

    val database = FirebaseDatabase.getInstance()
    val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/orderslist")
    private val ordersItems = mutableListOf<MyOrders>()
    val newChildRef: DatabaseReference = databaseReference.push()
    fun addItemToOrders(item: MyOrders) {

        println("i Am in ORDERS Befor adding  :$ordersItems")
        ordersItems.add(item)
        println("i Am in ORDERS aFTER ADDING  :$ordersItems")
        newChildRef.setValue(item)

    }
    var orderkey=newChildRef.getKey()

}