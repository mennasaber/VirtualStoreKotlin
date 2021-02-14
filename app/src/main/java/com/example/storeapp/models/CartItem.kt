package com.example.storeapp.models

class CartItem(
    var productSize: String,
    var productColor: String,
    var productCode: String,
    var productPrice: Float
) {
    fun getItem(): String {
        return "${productCode},${productSize},${productColor}&"
    }
}