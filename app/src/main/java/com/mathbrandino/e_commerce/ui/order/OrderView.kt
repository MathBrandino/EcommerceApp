package com.mathbrandino.e_commerce.ui.order

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathbrandino.e_commerce.R
import com.mathbrandino.e_commerce.data.local.item.OrdemItemAndProduct
import com.mathbrandino.e_commerce.data.local.item.OrderItem
import com.mathbrandino.e_commerce.data.local.order.Order
import com.mathbrandino.e_commerce.data.local.order.OrderWithItems
import com.mathbrandino.e_commerce.data.local.product.Product

@Composable
fun OrderView(order: OrderWithItems) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 4.dp,
        backgroundColor = Color.White

    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                text = stringResource(id = R.string.order_item_title, order.order.id),
                fontSize = 22.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(4.dp)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                order.items.forEach { order ->
                    OrderItemView(
                        quantity = order.orderItem.quantity.toString(),
                        name = order.product.name
                    )
                }
            }
            Divider(Modifier.padding(start = 16.dp, end = 16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    text = stringResource(id = R.string.order_value_title),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.product_value, order.order.total),
                    fontSize = 18.sp
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrderViewPreview() {
    OrderView(
        OrderWithItems(
            Order(id = 1, total = 20.0),
            items = listOf(
                OrdemItemAndProduct(
                    orderItem = OrderItem(
                        id = 1,
                        orderId = 1,
                        produtoId = 1,
                        quantity = 1
                    ),
                    product = Product(
                        id = 1,
                        description = "",
                        imageUrl = "",
                        name = "Bife",
                        value = 1.0
                    )
                )
            )
        )
    )
}