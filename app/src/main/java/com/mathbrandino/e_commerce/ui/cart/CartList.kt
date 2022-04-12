package com.mathbrandino.e_commerce.ui.cart

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mathbrandino.e_commerce.domain.model.CartItem

@Composable
fun ItemsList(
    modifier: Modifier,
    items: List<CartItem>,
    increase: (CartItem) -> Unit,
    decrease: (CartItem) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items,
            key = { cartItem -> cartItem.product.id },
        ) { item: CartItem -> CartItemView(item, increase, decrease) }
    }
}