package com.mathbrandino.e_commerce.ui.list

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.databinding.ActivityMainBinding
import com.mathbrandino.e_commerce.ui.cart.CartActivity
import com.mathbrandino.e_commerce.ui.detail.ProductDetailActivity
import com.mathbrandino.e_commerce.ui.form.ProductFormActivity
import com.mathbrandino.e_commerce.ui.order.OrderActivity
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
            binding.productList.adapter = ProductAdapter(it, this::clickListCallback)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mainMenuCartItem -> {
                goToCart()
            }
            R.id.mainMenuOrders -> {
                goToOrders()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun goToOrders() {
        startActivity(Intent(this, OrderActivity::class.java))
    }

    private fun goToCart() {
        startActivity(Intent(this, CartActivity::class.java))
    }

    private fun clickListCallback(product: Product, view: View) {

        val makeSceneTransitionAnimation = ActivityOptions
            .makeSceneTransitionAnimation(this, view, "productImage")

        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra(ProductDetailActivity.PRODUCT_EXTRA, product)

        startActivity(intent, makeSceneTransitionAnimation.toBundle())
    }
}