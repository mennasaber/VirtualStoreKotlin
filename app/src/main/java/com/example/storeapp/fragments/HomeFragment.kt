package com.example.storeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapp.R
import com.example.storeapp.activities.MainActivity
import com.example.storeapp.adapters.ProductsAdapter
import com.example.storeapp.databinding.FragmentHomeBinding
import com.example.storeapp.databinding.LayoutTopMainToolbarBinding
import com.example.storeapp.viewmodels.HomeFragmentVM

class HomeFragment : Fragment(), View.OnClickListener {
    private val model: HomeFragmentVM by viewModels()
    lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding
    private lateinit var toolbarBinding: LayoutTopMainToolbarBinding
    private lateinit var currentActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        toolbarBinding = LayoutTopMainToolbarBinding.bind(binding.root)
        currentActivity = activity as MainActivity
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
        toolbarBinding.cartB.setOnClickListener(this)
        if (currentActivity.model.cart.count() != 0)
            toolbarBinding.cartB.visibility = View.VISIBLE
        else
            toolbarBinding.cartB.visibility = View.INVISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupUI()
    }

    override fun onClick(v: View) {
        when (v.id) {
            toolbarBinding.cartB.id -> {
                navController.navigate(R.id.action_homeFragment_to_cartFragment)
            }
        }
    }

}