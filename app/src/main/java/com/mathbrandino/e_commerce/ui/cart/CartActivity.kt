package com.mathbrandino.e_commerce.ui.cart

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.databinding.ActivityCartBinding
import com.mathbrandino.e_commerce.domain.model.Cart
import com.mathbrandino.e_commerce.domain.useCases.CreateOrderUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class CartActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var cart: Cart

    @Inject
    lateinit var createOrderUseCase: CreateOrderUseCase

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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.cart_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.cartMenuFinish -> {
                lifecycleScope.launch {
                    try {
                        createOrderUseCase.save(cart)
                        finish()
                        Toast.makeText(
                            this@CartActivity,
                            "Pedido fechado com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (error: Exception) {
                        Toast.makeText(this@CartActivity, error.message, Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}