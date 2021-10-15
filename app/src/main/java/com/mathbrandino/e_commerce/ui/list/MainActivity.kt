package com.mathbrandino.e_commerce.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.mathbrandino.e_commerce.databinding.ActivityMainBinding
import com.mathbrandino.e_commerce.ui.form.ProductFormActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fabAddProduct.setOnClickListener {
            startActivity(Intent(this, ProductFormActivity::class.java))
        }

        viewModel.products.observe(this) {
            Toast.makeText(this, "${it.size}", Toast.LENGTH_SHORT).show()
        }
    }
}