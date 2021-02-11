package com.example.storeapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storeapp.models.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class HomeFragmentVM : ViewModel() {
    private val productsCodes: MutableLiveData<ArrayList<Product>> = MutableLiveData()
    fun getProducts(): LiveData<ArrayList<Product>> {
        FirebaseFirestore.getInstance().collection("Products").addSnapshotListener { value, _ ->
            productsCodes.postValue(value?.let { it -> toProducts(it) })
        }
        return productsCodes
    }

    private fun toProducts(it: QuerySnapshot): ArrayList<Product> {
        val list = arrayListOf<Product>()
        for (document in it) {
            val product = Product(
                id = document.id,
                code = document["code"] as String,
                material = document["material"] as String,
                colors = document["colors"] as String,
                image = document["image"] as String?,
                price = (document["price"] as Double).toFloat(),
                details = document["details"] as String,
                size = document["size"] as String
            )
            list.add(product)
        }
        return list
    }
}