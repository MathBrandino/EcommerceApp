package com.mathbrandino.e_commerce.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.ui.cart.CartActivity
import com.mathbrandino.e_commerce.ui.common.TopBarWithoutBack
import com.mathbrandino.e_commerce.ui.detail.ProductDetailActivity
import com.mathbrandino.e_commerce.ui.form.ProductFormActivity
import com.mathbrandino.e_commerce.ui.order.OrderActivity
import com.mathbrandino.e_commerce.ui.theme.EcommerceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val orders: List<Product> by viewModel.products.collectAsState(listOf())
                    Scaffold(
                        floatingActionButton = {
                            ExtendedFloatingActionButton(
                                text = {
                                    Text(
                                        text = stringResource(id = R.string.main_activity_fab_text),
                                        style = TextStyle.Default.copy(color = Color.White),
                                        fontWeight = FontWeight.Bold,
                                    )
                                },
                                onClick = { goToForm() },
                                icon = { Icon(Icons.Filled.Add, "add", tint = Color.White) },
                            )
                        },
                        content = {
                            Column {
                                TopBarWithoutBack(
                                    title = stringResource(id = R.string.main_activity_title),
                                    actions = {
                                        IconButton(onClick = { goToCart() }) {
                                            Icon(Icons.Filled.ShoppingCart, "shopping cart")
                                        }
                                        IconButton(onClick = { goToOrders() }) {
                                            Icon(Icons.Filled.List, "orders")
                                        }
                                    }
                                )
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    items(orders) { item ->
                                        ProductView(item, onClick = { clickListCallback(item) })
                                    }
                                }
                            }
                        })
                }
            }
        }
    }

    private fun goToForm() {
        startActivity(Intent(this, ProductFormActivity::class.java))
    }

    private fun goToOrders() {
        startActivity(Intent(this, OrderActivity::class.java))
    }

    private fun goToCart() {
        startActivity(Intent(this, CartActivity::class.java))
    }

    private fun clickListCallback(product: Product) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra(ProductDetailActivity.PRODUCT_EXTRA, product)
        startActivity(intent)
    }
}