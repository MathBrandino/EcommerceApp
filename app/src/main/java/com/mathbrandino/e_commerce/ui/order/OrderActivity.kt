package com.mathbrandino.e_commerce.ui.order

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {

    private val viewModel : OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.orders.observe(this) {
            Log.d("orders", "$it")
        }
    }

}