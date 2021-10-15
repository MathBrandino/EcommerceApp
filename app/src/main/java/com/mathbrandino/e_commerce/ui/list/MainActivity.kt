package com.mathbrandino.e_commerce.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
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
            binding.productList.adapter = ProductAdapter(it)
        }

        binding.productList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 || dy < 0 && binding.fabAddProduct.isShown) binding.fabAddProduct.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) binding.fabAddProduct.show()
            }
        })
    }
}