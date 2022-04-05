package com.mathbrandino.e_commerce.ui.order

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.order.OrderWithItems
import com.mathbrandino.e_commerce.databinding.ActivityOrderBinding
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
    val orders: List<OrderWithItems> by viewModel.orders.observeAsState(listOf())
    Column {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.order_title)) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Filled.ArrowBack, "back button")
                }
            },
        )
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