package com.example.storeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.storeapp.R
import com.example.storeapp.activities.MainActivity
import com.example.storeapp.databinding.FragmentUserDataBinding
import com.example.storeapp.viewmodels.OrderVM

class UserDataFragment : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentUserDataBinding
    private lateinit var navController: NavController
    private lateinit var currentActivity: MainActivity
    private val model: OrderVM by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDataBinding.inflate(inflater, container, false)
        currentActivity = activity as MainActivity
        binding.orderB.setOnClickListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.orderB.id -> {
                if (isValid()) {
                    saveOrder()
                    displayDialog()
                    navController.navigate(R.id.action_userDataFragment_to_homeFragment)
                } else
                    setErrors()
            }
        }
    }

    private fun setErrors() {
        if (binding.addressET.text.isEmpty())
            binding.addressET.error = "Required"
        if (binding.countryET.text.isEmpty())
            binding.countryET.error = "Required"
        if (binding.nameET.text.isEmpty())
            binding.nameET.error = "Required"
        if (binding.phoneET.text.isEmpty())
            binding.phoneET.error = "Required"
    }

    private fun displayDialog() {
        val builder: android.app.AlertDialog.Builder =
            android.app.AlertDialog.Builder(requireContext())
        val dialogView =
            LayoutInflater.from(requireContext())
                .inflate(R.layout.fragment_confirm_order, null)
        val dialog = builder.create()
        dialog.setView(dialogView)
        dialog.show()
    }

    private fun saveOrder() {
        currentActivity.model.cart.forEach { currentActivity.model.order.cart += it.getItem() }
        currentActivity.model.order.userName = binding.nameET.text.toString()
        currentActivity.model.order.userPhone = binding.phoneET.text.toString()
        currentActivity.model.order.userAddress = binding.addressET.text.toString()
        currentActivity.model.order.userCountry = binding.countryET.text.toString()
        model.saveOrder(currentActivity.model.order)
        currentActivity.model.reset()
    }

    private fun isValid(): Boolean {
        return binding.addressET.text.isNotEmpty()
                && binding.countryET.text.isNotEmpty()
                && binding.nameET.text.isNotEmpty()
                && binding.phoneET.text.isNotEmpty()
    }
}