package com.example.storeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductsAdapter(private val productsList: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTxt: TextView = view.findViewById(R.id.productName)
        val priceTxt: TextView = view.findViewById(R.id.productPrice)
        val productImage = view.findViewById<ImageView>(R.id.productImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        product.apply {
            holder.nameTxt.text = name
            holder.priceTxt.text = "$price $"
        }

    }

    override fun getItemCount(): Int {
        return if (productsList.count() > 10) {
            10
        } else {
            productsList.count()
        }
    }
}