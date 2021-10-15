package com.mathbrandino.e_commerce.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mathbrandino.e_commerce.databinding.ActivityProductFormBinding

class ProductFormActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}