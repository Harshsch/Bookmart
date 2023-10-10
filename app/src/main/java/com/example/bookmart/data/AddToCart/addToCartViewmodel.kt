package com.example.bookmart.data.AddToCart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

data class Address(
    val id: String = "0",
    val name: String = "",
    val streetAddress: String = "",
    val city: String = "",
    val mobileNumber: String? = null,
    //@get:PropertyName("default")
    //val isDefault: Boolean = false
)



data class CartItem(
    val id:Int =0,
    val name: String = "",
    val price: Int = 0,

)

data class MyOrders(

    val name: String = "",
    val payment: String = "",
    val status: String = "",
)
class MyOrdersViewModel : ViewModel() {
    val userId = FirebaseAuth.getInstance().currentUser?.uid // Get the user's ID

    val database = FirebaseDatabase.getInstance()
    val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/orderslist")
    private val ordersItems = mutableListOf<MyOrders>()

    // Function to add an item to the cart
    fun addItemToOrders(item: MyOrders) {

        println("i Am in ORDERS Befor adding  :$ordersItems")
        ordersItems.add(item)
        println("i Am in ORDERS aFTER ADDING  :$ordersItems")
        databaseReference.push().setValue(item)
    }

    // Function to get the current cart items
    fun getCartItems(): List<MyOrders> {
        return ordersItems.toList()

    }
}


class CartViewModel : ViewModel() {
    val userId = FirebaseAuth.getInstance().currentUser?.uid // Get the user's ID

    val database = FirebaseDatabase.getInstance()
    val databaseReference = FirebaseDatabase.getInstance().getReference("users/$userId/cartlist")
    private val cartItems = mutableListOf<CartItem>()

    // Function to add an item to the cart
    fun addItemToCart(item: CartItem) {
        println("i Am in cart Befor adding  :$cartItems")
        cartItems.add(item)
        println("i Am in cart aFTER ADDING  :$cartItems")
        databaseReference.push().setValue(item)
    }

    // Function to get the current cart items
    fun getCartItems(): List<CartItem> {
        return cartItems.toList()

    }
}

class AddressViewModel : ViewModel() {
    var id: Int by mutableStateOf(0)
}
