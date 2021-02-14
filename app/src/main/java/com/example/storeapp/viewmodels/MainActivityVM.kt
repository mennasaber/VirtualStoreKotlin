package com.example.storeapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.storeapp.models.CartItem
import com.example.storeapp.models.Order

class MainActivityVM : ViewModel() {
    var order: Order = Order("", null, null, null, null)
    var cart: ArrayList<CartItem> = arrayListOf()
    fun reset() {
        order = Order("", null, null, null, null)
        cart = arrayListOf()
    }
}