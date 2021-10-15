package com.mathbrandino.e_commerce.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.databinding.ActivitiyProductDetailBinding
import com.mathbrandino.e_commerce.ui.form.ProductFormActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity : AppCompatActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val binding by lazy {
        ActivitiyProductDetailBinding.inflate(layoutInflater)
    }
    private val product by lazy {
        intent!!.getParcelableExtra<Product>(PRODUCT_EXTRA)!!
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = product.name
        binding.productDetailImage.load(product.imageUrl)
        binding.productDetailDescriptionText.text = product.description
        binding.productDetailValueText.text = getString(
            R.string.product_detail_value,
            product.value
        )

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    finish()
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        R.id.productDetailEditMenu -> {
            val intent = Intent(this, ProductFormActivity::class.java)
            intent.putExtra(ProductFormActivity.PRODUCT_KEY, product)
            resultLauncher.launch(intent)
            true
        }
        else -> true
    }

    companion object {
        const val PRODUCT_EXTRA = "product"
    }
}