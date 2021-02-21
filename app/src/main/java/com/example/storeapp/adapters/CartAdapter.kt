package com.example.storeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.databinding.CartItemBinding
import com.example.storeapp.interfaces.OnRemoveClickListener
import com.example.storeapp.models.CartItem

class CartAdapter(private var cartList: ArrayList<CartItem>,var onRemoveClickListener: OnRemoveClickListener) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: CartItemBinding = CartItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.binding.apply {
            productCodeTV.text = cartList[position].productCode
            productColorTV.text = cartList[position].productColor
            productSizeTV.text = cartList[position].productSize
            productPriceTV.text = cartList[position].productPrice.toString() + " LE."

            deleteProductIV.setOnClickListener {
                onRemoveClickListener.onRemove(cartList[position].productPrice)
                cartList.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return cartList.count()
    }
}