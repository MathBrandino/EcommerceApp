package com.mathbrandino.e_commerce.ui.cart

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.domain.model.CartItem

@Composable
fun CartItemCounter(item: CartItem, increase: (CartItem) -> Unit, decrease: (CartItem) -> Unit) {
    var quantity by remember {
        mutableStateOf(item.quantity)
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            increase(item)
            quantity++
        }) {
            Icon(Icons.Filled.KeyboardArrowUp, "back button")
        }
        Text(
            text = quantity.toString(),
            textAlign = TextAlign.Center,

            )
        IconButton(onClick = {
            decrease(item)
            quantity--
        }) {
            Icon(Icons.Filled.KeyboardArrowDown, "back button")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CartItemCounterPreview() {
    CartItemCounter(
        item = CartItem(
            Product(
                name = "Acabaxi",
                value = 2.0,
                id = 0,
                description = null,
                imageUrl = "https://www.altoastral.com.br/media/_versions/legacy/2016/12/abacaxi-capa_widexl.jpg"
            ), quantity = 1
        ),
        increase = {},
        decrease = {},
    )
}