package com.example.storeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.adapters.ProductsAdapter
import com.example.storeapp.viewmodels.HomeFragmentVM

class HomeFragment : Fragment() {
    private val model: HomeFragmentVM by viewModels()
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val productsRecyclerView = view.findViewById<RecyclerView>(R.id.productsRecyclerView)
        val width = resources.configuration.screenWidthDp
        val spanCount = width / 155
        val linearLayoutManager = GridLayoutManager(requireContext(), spanCount)

        model.getProducts().observe(requireActivity(), {
            productsRecyclerView.adapter =
                ProductsAdapter(it, 1, navController)
            productsRecyclerView.layoutManager = linearLayoutManager
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}