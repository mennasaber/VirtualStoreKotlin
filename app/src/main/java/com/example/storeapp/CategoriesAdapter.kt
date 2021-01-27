package com.example.storeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(private val categoriesList: List<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productsRecyclerView: RecyclerView =
            view.findViewById(R.id.productsRecyclerView)
        val nameTxt: TextView = view.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoriesList[position]
        category.apply {
            holder.nameTxt.text = category.name
        }
        val linearLayoutManager = LinearLayoutManager(
            holder.productsRecyclerView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val productsAdapter = categoriesList[position].products?.let { ProductsAdapter(it) }
        holder.productsRecyclerView.layoutManager = linearLayoutManager
        holder.productsRecyclerView.adapter = productsAdapter
    }

    override fun getItemCount(): Int = categoriesList.count()
}




