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
import com.example.storeapp.adapters.ProductsAdapter
import com.example.storeapp.databinding.FragmentHomeBinding
import com.example.storeapp.viewmodels.HomeFragmentVM

class HomeFragment : Fragment() {
    private val model: HomeFragmentVM by viewModels()
    lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        val productsRecyclerView = binding.productsRecyclerView
        val screenWidth = resources.configuration.screenWidthDp
        val spanCount = screenWidth / 155
        val gridLayoutManager = GridLayoutManager(requireContext(), spanCount)

        model.getProducts().observe(requireActivity(), {
            productsRecyclerView.adapter =
                ProductsAdapter(it, navController)
            productsRecyclerView.layoutManager = gridLayoutManager
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}