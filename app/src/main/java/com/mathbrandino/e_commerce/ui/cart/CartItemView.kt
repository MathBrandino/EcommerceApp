package com.mathbrandino.e_commerce.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.domain.model.CartItem


@Composable
fun CartItemView(item: CartItem, increase: (CartItem) -> Unit, decrease: (CartItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = item.product.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
        Column() {
            Text(
                text = item.product.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),

                )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                CartItemCounter(item, increase, decrease)
                Text(
                    text = stringResource(
                        R.string.product_value,
                        item.getTotal()
                    ),
                    textAlign = TextAlign.Center,
                )
            }
            Divider(modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartItemViewPreview() {
    CartItemView(
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