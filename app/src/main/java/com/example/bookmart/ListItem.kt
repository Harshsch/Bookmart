package com.example.bookmart

data class ListItem(val id: Int, val imageResId: Int, val name: String,val author:String)

val itemList = listOf(
    ListItem(1, R.drawable.img_1, "Rs. 375.00","COMPUTER NETWORKS"),
    ListItem(2, R.drawable.img_2, "Rs. 245.00","CRYPTOGRAPHY AND CYBER SECURITY"),
    ListItem(3, R.drawable.img_3, "Rs. 195.00","ANDROID DEVELOPMENT"),
    ListItem(4, R.drawable.img_4, "Rs. 260.00","BLOCKCHAIN TECHNOLOGY"),
    ListItem(5, R.drawable.img_5, "Rs. 245.00","CLOUD COMPUTING"),
    ListItem(6, R.drawable.img_6, "Rs. 415.00","COMPUTER AIDED ENGINEERING"),
    ListItem(7, R.drawable.img_7, "Rs. 275.00","CELLULAR NETWORKS"),
    ListItem(8, R.drawable.img_8, "Rs. 415.00","COMPUTER GRAPHICS"),
    ListItem(9, R.drawable.img_9, "Rs. 105.00","DATA SCIENCE "),
    ListItem(10, R.drawable.img_10, "Rs. 295.00","DEEP LEARNING"),


    // Add more items as needed
)
