package com.mathbrandino.e_commerce.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.size.Scale
import com.mathbrandino.e_commerce.databinding.ActivityProductFormBinding

class ProductFormActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.productFormImageUrl.editText?.apply {
            setOnEditorActionListener { _, _, _ ->
                searchImage(text.toString())
                true
            }
        }
        binding.productFormImageSearch.setOnClickListener {
            searchImage(binding.productFormImageUrl.editText?.text.toString())
        }
    }

    private fun searchImage(link: String) {
        binding.productFormImage.load(link) {
            scale(Scale.FIT)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> true
    }
}