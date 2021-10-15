package com.mathbrandino.e_commerce.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.databinding.ActivitiyProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import java.math.RoundingMode

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitiyProductDetailBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val product = intent!!.getParcelableExtra<Product>(PRODUCT_EXTRA)!!

        supportActionBar?.title = product.name
        binding.productDetailImage.load(product.imageUrl)
        binding.productDetailDescriptionText.text = product.description
        binding.productDetailValueText.text = getString(
            R.string.product_detail_value,
            product.value
        )

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> true
    }

    companion object {
        const val PRODUCT_EXTRA = "product"
    }
}