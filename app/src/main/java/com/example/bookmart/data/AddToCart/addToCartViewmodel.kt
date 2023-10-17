package com.example.bookmart.data.AddToCart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class Address(
    val id: String = "0",
    val name: String = "",
    val streetAddress: String = "",
    val city: String = "",
    val mobileNumber: String? = null,
)

data class CartItem(
    val id:Int =0,
    val name: String = "",
    val price: Int = 0,
    val quantity:Int=1,
    val imageResId: Int=0,
   // val cartlistkey:String="",

)

data class MyOrders(

    val id:Int=0,
    val name: String = "",
    val payment: String = "",
    val status: String = "",
    val imageResIdorder: Int=0,
    val orderkey:String="",
    val orderlistkey:String="",
)
class MyOrdersViewModel : ViewModel() {
    val userId = FirebaseAuth.getInstance().currentUser?.uid // Get the user's ID

    val database = FirebaseDatabase.getInstance()
    val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/orderslist")
    private val ordersItems = mutableListOf<MyOrders>()
    val newChildRef: DatabaseReference = databaseReference.push()

    // Function to add an item to the cart
    fun addItemToOrders(item: MyOrders) {

        println("i Am in ORDERS Befor adding  :$ordersItems")
        ordersItems.add(item)
        println("i Am in ORDERS aFTER ADDING  :$ordersItems")
        newChildRef.setValue(item)

    }
    var orderkey=newChildRef.getKey()

    // Function to get the current cart items
}


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

class AddressViewModel : ViewModel() {
    var id: Int by mutableStateOf(0)
}
