package com.example.storeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.storeapp.R
import com.example.storeapp.models.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
    lateinit var navController: NavController
    private lateinit var product: Product
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        setupUI(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        view.findViewById<ImageView>(R.id.backIV).setOnClickListener {
            navController.navigate(R.id.action_detailsFragment_to_homeFragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = requireArguments().getParcelable("product")!!
    }

    private fun setupUI(view: View) {
        view.findViewById<TextView>(R.id.codeTV).text = product.code
        view.findViewById<TextView>(R.id.priceTV).text = "${product.price} LE."
        view.findViewById<TextView>(R.id.materialTV).text = product.material
        view.findViewById<TextView>(R.id.detailsTV).text = product.details
        view.findViewById<TextView>(R.id.sizeTV).text = product.size
        view.findViewById<TextView>(R.id.colorsTV).text = product.colors
        val productImageView = view.findViewById<ImageView>(R.id.productIV)
        val progress = view.findViewById<ProgressBar>(R.id.progressBar)
        progress.visibility = View.VISIBLE
        Picasso.get().load(product.image).into(productImageView, object : Callback {
            override fun onSuccess() {
                progress.visibility = View.INVISIBLE
            }

            override fun onError(e: Exception?) {
                progress.visibility = View.INVISIBLE
            }
        })
        productImageView.setOnClickListener {
            val bundle = bundleOf("product" to product)
            navController.navigate(R.id.action_detailsFragment_to_imageFragment, bundle)
        }
    }
}