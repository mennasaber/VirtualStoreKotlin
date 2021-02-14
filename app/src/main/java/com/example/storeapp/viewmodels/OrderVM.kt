package com.example.storeapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.storeapp.models.Order
import com.google.firebase.firestore.FirebaseFirestore

class OrderVM : ViewModel() {
    fun saveOrder(order: Order) {
        FirebaseFirestore.getInstance().collection("Orders").add(order)
    }
}