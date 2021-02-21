package com.example.storeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import com.example.storeapp.models.Product
import com.example.storeapp.viewmodels.HomeFragmentVM

class HomeFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private val model: HomeFragmentVM by viewModels()
    lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding
    private lateinit var toolbarBinding: LayoutTopMainToolbarBinding
    private lateinit var currentActivity: MainActivity
    private var products: ArrayList<Product> = arrayListOf()
    private var spanCount = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        toolbarBinding = LayoutTopMainToolbarBinding.bind(binding.root)
        currentActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupUI()
    }

    private fun setupUI() {
        val screenWidth = resources.configuration.screenWidthDp
        spanCount = screenWidth / 155

        val categories = arrayListOf<String>()
        categories.add("All")
        categories.add("Offers")
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )
        model.getProducts().observe(requireActivity(), {
            products = it
            binding.progressBar.visibility = View.INVISIBLE
            toolbarBinding.spinner.adapter = arrayAdapter
            toolbarBinding.spinner.setSelection(model.spinnerPosition)
            toolbarBinding.spinner.onItemSelectedListener = this
        })
        toolbarBinding.cartB.setOnClickListener(this)
        if (currentActivity.model.cart.count() != 0)
            toolbarBinding.cartB.visibility = View.VISIBLE
        else
            toolbarBinding.cartB.visibility = View.GONE
    }


    override fun onClick(v: View) {
        when (v.id) {
            toolbarBinding.cartB.id -> {
                navController.navigate(R.id.action_homeFragment_to_cartFragment)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        model.spinnerPosition = position
        when (position) {
            0 -> {
                binding.productsRecyclerView.adapter =
                    ProductsAdapter(products, navController)
            }
            1 -> {
                binding.productsRecyclerView.adapter =
                    ProductsAdapter(products.filter { it.offer != 0f }, navController)
            }
        }
        binding.productsRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), spanCount)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}