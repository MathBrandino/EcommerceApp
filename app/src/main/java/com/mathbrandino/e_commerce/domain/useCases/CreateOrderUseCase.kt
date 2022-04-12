package com.mathbrandino.e_commerce.domain.useCases

import com.mathbrandino.e_commerce.data.local.item.OrderItem
import com.mathbrandino.e_commerce.data.local.item.OrderItemDao
import com.mathbrandino.e_commerce.data.local.order.Order
import com.mathbrandino.e_commerce.data.local.order.OrderDao
import com.mathbrandino.e_commerce.domain.model.Cart
import javax.inject.Inject

class CreateOrderUseCase @Inject constructor(
    private val orderDao: OrderDao,
    private val orderItemDao: OrderItemDao
) {

    suspend fun save(cart: Cart) {

        val order = Order(0, cart.total.value.toDouble())

        val orderSavedId = orderDao.create(order)

        val orderItems =
            cart.items.map { item -> OrderItem(0, item.product.id, orderSavedId.toInt(), item.quantity) }
                .toTypedArray()

        orderItemDao.create(*orderItems)

        cart.clear()

    }

}