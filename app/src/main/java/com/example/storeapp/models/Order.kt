package com.example.storeapp.models

data class Order(
    var cart: String,
    var userPhone: String?,
    var userCountry: String?,
    var userAddress: String?,
    var userName: String?,
)