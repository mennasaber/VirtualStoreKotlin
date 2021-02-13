package com.example.storeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentImageBinding
import com.example.storeapp.databinding.LayoutTopImageToolbarBinding
import com.example.storeapp.models.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageFragment : Fragment(), View.OnClickListener {
    lateinit var product: Product
    private lateinit var binding: FragmentImageBinding
    private lateinit var toolbarbinding: LayoutTopImageToolbarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        toolbarbinding = LayoutTopImageToolbarBinding.bind(binding.root)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        binding.progressBar.visibility = View.VISIBLE
        Picasso.get().load(product.image)
            .into(binding.productIV, object :
                Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {
                    binding.progressBar.visibility = View.INVISIBLE
                }
            })
        toolbarbinding.backIV.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = requireArguments().getParcelable("product")!!
    }

    override fun onClick(v: View) {
        when (v.id) {
            toolbarbinding.backIV.id -> {
                val bundle = bundleOf("product" to product)
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_imageFragment_to_detailsFragment, bundle)
            }
        }
    }
}