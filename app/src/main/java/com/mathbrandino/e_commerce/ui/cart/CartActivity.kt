package com.mathbrandino.e_commerce.ui.cart

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.databinding.ActivityCartBinding
import com.mathbrandino.e_commerce.domain.model.Cart
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var cart: Cart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        updateCartTotal()

        binding.cartItems.adapter = CartAdapter(
            cart.items,
            add = { cartItem ->
                cart.increaseQuantityOf(cartItem.product)
                updateCartTotal()
            },
            minus = { cartItem ->
                val removed = cart.decreaseQuantityOf(cartItem.product)
                updateCartTotal()
                removed
            })
    }

    private fun updateCartTotal() {
        binding.cartValueText.text = getString(R.string.cart_total_text, cart.getTotal().toDouble())
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}