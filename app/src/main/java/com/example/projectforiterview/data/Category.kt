package com.example.projectforiterview.data

data class Category(
    val name : String,
    val imageUrl : String,
    var totalDistinct : Int = 0,
    val products: MutableList<Product> = mutableListOf()
) {
    val totalStock: Int = products.size
}
