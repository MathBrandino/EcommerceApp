package com.mathbrandino.e_commerce.ui.cart

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.domain.model.Cart
import com.mathbrandino.e_commerce.domain.useCases.CreateOrderUseCase
import com.mathbrandino.e_commerce.ui.common.TopBar
import com.mathbrandino.e_commerce.ui.theme.EcommerceTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CartActivity : ComponentActivity() {

    @Inject
    lateinit var cart: Cart

    @Inject
    lateinit var createOrderUseCase: CreateOrderUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val total by remember { cart.total }
                    Column {
                        TopBar(
                            onBackPressed = { finish() },
                            title = stringResource(id = R.string.cart_title),
                            actions = {
                                IconButton(onClick = {
                                    lifecycleScope.launch {
                                        createOrderUseCase.save(cart)
                                        finish()
                                        Toast.makeText(
                                            this@CartActivity,
                                            "Pedido fechado com sucesso",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }) {
                                    Icon(
                                        Icons.Default.Done,
                                        contentDescription = "fechar pedido"
                                    )
                                }
                            })
                        ItemsList(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1F),
                            cart.items,
                            increase = { item -> cart.increaseQuantityOf(item.product) },
                            decrease = { item -> cart.decreaseQuantityOf(item.product) }
                        )
                        CartTotal(total)
                    }
                }
            }
        }
    }
}