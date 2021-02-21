package com.example.storeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeapp.R
import com.example.storeapp.activities.MainActivity
import com.example.storeapp.adapters.CartAdapter
import com.example.storeapp.databinding.FragmentCartBinding
import com.example.storeapp.databinding.LayoutTopDetailsToolbarBinding
import com.example.storeapp.interfaces.OnRemoveClickListener

class CartFragment : Fragment(), View.OnClickListener, OnRemoveClickListener {
    lateinit var binding: FragmentCartBinding
    private lateinit var toolbarBinding: LayoutTopDetailsToolbarBinding
    private lateinit var navController: NavController
    private lateinit var currentActivity: MainActivity
    var total = 0f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        toolbarBinding = LayoutTopDetailsToolbarBinding.bind(binding.root)
        currentActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        setupUI()
    }

    private fun setupUI() {
        binding.cartRecyclerView.adapter = CartAdapter(currentActivity.model.cart, this)
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        currentActivity.model.cart.forEach { total += it.productPrice }
        binding.totalPriceTV.text = total.toString()

        toolbarBinding.backIV.setOnClickListener(this)
        binding.confirmOrderB.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            toolbarBinding.backIV.id -> {
                navController.navigate(R.id.action_cartFragment_to_homeFragment)
            }
            binding.confirmOrderB.id -> {
                if (currentActivity.model.cart.count() != 0) {
                    navController.navigate(R.id.action_cartFragment_to_userDataFragment)
                }
            }
        }
    }

    override fun onRemove(removedPrice: Float) {
        total -= removedPrice
        binding.totalPriceTV.text = total.toString()
    }
}