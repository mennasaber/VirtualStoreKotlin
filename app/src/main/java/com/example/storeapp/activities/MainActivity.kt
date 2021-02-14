package com.example.storeapp.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.storeapp.R
import com.example.storeapp.models.Order
import com.example.storeapp.viewmodels.MainActivityVM

class MainActivity : AppCompatActivity() {
    val model: MainActivityVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}