package com.example.storeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val categoriesRecyclerView = view.findViewById<RecyclerView>(R.id.categoriesRecyclerView)
        val linearLayoutManager = LinearLayoutManager(view.context)

        val productsList = arrayListOf<Product>()
        productsList.add(Product("0", "Product1", 100f, ""))
        productsList.add(Product("0", "Product2", 220f, ""))
        productsList.add(Product("0", "Product3", 99f, ""))
        productsList.add(Product("0", "Product4", 100f, ""))

        val categoriesList = arrayListOf<Category>()
        categoriesList.add(Category("0", "Category1", productsList))
        categoriesList.add(Category("0", "Category2", productsList))
        categoriesList.add(Category("0", "Category3", productsList))
        categoriesList.add(Category("0", "Category4", productsList))

        val categoriesAdapter = CategoriesAdapter(categoriesList)
        categoriesRecyclerView.adapter = categoriesAdapter
        categoriesRecyclerView.layoutManager = linearLayoutManager
        return view
    }
}