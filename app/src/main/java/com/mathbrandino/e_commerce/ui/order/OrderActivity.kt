package com.mathbrandino.e_commerce.ui.order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.order.OrderWithItems
import com.mathbrandino.e_commerce.ui.common.TopBar
import com.mathbrandino.e_commerce.ui.theme.EcommerceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : ComponentActivity() {

    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Screen(viewModel = viewModel, onBackClick = { finish() })
                }
            }
        }
    }
}

@Composable
fun Screen(viewModel: OrderViewModel, onBackClick: () -> Unit) {
    val orders: List<OrderWithItems> by viewModel.orders.collectAsState(listOf())
    Column {
        TopBar(onBackPressed = onBackClick, title = stringResource(id = R.string.order_title))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(orders) { item ->
                OrderView(item)
            }
        }
    }
}