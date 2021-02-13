package com.example.storeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentDetailsBinding
import com.example.storeapp.databinding.LayoutTopDetailsToolbarBinding
import com.example.storeapp.models.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var product: Product
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var toolbarBinding: LayoutTopDetailsToolbarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        toolbarBinding = LayoutTopDetailsToolbarBinding.bind(binding.root)
        setupUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        toolbarBinding.backIV.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = requireArguments().getParcelable("product")!!
    }

    private fun setupUI() {
        binding.apply {
            codeTV.text = product.code
            priceTV.text = "${product.price} LE."
            materialTV.text = product.material
            detailsTV.text = product.details
            sizeTV.text = product.size
            colorsTV.text = product.colors
            progressBar.visibility = View.VISIBLE

            Picasso.get().load(product.image).into(productIV, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.INVISIBLE
                }
            })
        }
        binding.productIV.setOnClickListener(this)
        binding.addToCartB.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.productIV.id -> {
                val bundle = bundleOf("product" to product)
                navController.navigate(R.id.action_detailsFragment_to_imageFragment, bundle)
            }
            binding.addToCartB.id -> {
                val builder: android.app.AlertDialog.Builder =
                    android.app.AlertDialog.Builder(requireContext())
                builder.setView(R.layout.add_to_cart_layout)
                builder.create()?.show()
            }
            toolbarBinding.backIV.id -> {
                navController.navigate(R.id.action_detailsFragment_to_homeFragment)
            }
        }

    }
}