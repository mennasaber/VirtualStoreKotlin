package com.example.storeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.models.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val productsList: List<Product>,
    private val type: Int,
    private var navController: NavController
) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTxt: TextView = view.findViewById(R.id.productName)
        val priceTxt: TextView = view.findViewById(R.id.productPrice)
        val productImage: ImageView = view.findViewById(R.id.productImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productsList[position]
        product.apply {
            holder.nameTxt.text = "Code $code"
            holder.priceTxt.text = "$price LE."
            val progress = holder.itemView.findViewById<ProgressBar>(R.id.progressBar)
            progress.visibility = View.VISIBLE
            Picasso.get().load(image).into(holder.productImage, object : Callback {
                override fun onSuccess() {
                    progress.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {
                    progress.visibility = View.INVISIBLE
                }
            })
        }
        holder.itemView.setOnClickListener {
            val bundle = bundleOf("product" to productsList[position])
            navController.navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return if (type == 0) {
            10
        } else {
            productsList.count()
        }
    }
}