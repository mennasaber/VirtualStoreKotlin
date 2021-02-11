package com.example.storeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.storeapp.models.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageFragment : Fragment() {
    lateinit var product: Product
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        val progress = view.findViewById<ProgressBar>(R.id.progressBar)
        progress.visibility = View.VISIBLE
        Picasso.get().load(product.image)
            .into(view.findViewById(R.id.productIV), object :
                Callback {
                override fun onSuccess() {
                    progress.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {
                    progress.visibility = View.INVISIBLE
                }
            })
        view.findViewById<ImageView>(R.id.backIV).setOnClickListener {
            val bundle = bundleOf("product" to product)
            Navigation.findNavController(view)
                .navigate(R.id.action_imageFragment_to_detailsFragment, bundle)
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = requireArguments().getParcelable("product")!!
    }
}