package com.mathbrandino.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mathbrandino.e_commerce.databinding.ActivityMainBinding
import com.mathbrandino.e_commerce.ui.ProductFormActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAddProduct.setOnClickListener {
            startActivity(Intent(this, ProductFormActivity::class.java))
        }
    }
}