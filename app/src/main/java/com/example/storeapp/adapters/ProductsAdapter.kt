package com.example.storeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.databinding.ProductItemBinding
import com.example.storeapp.models.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val productsList: List<Product>,
    private var navController: NavController
) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ProductItemBinding = ProductItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        product.apply {
            holder.binding.apply {
                productName.text = "Code $code"
                productPrice.text = "$price LE."
                progressBar.visibility = View.VISIBLE
                Picasso.get().load(image).into(productImage, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.INVISIBLE
                    }

                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.INVISIBLE
                    }
                })
            }
        }
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("product" to productsList[position])
            navController.navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return productsList.count()
    }
}