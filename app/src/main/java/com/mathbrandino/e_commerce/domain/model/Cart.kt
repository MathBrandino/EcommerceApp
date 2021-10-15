package com.mathbrandino.e_commerce.domain.model

import com.mathbrandino.e_commerce.data.local.product.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Cart @Inject constructor() {

    val items = HashSet<CartItem>()

    fun getTotal() = items.sumOf { it.getTotal() }

    fun add(product: Product) {
        items.add(CartItem(product, 1))
    }

    fun increaseQuantityOf(product: Product) {
        val item = items.find { it.product == product }
        item?.quantity = item?.quantity?.plus(1)!!
    }

    fun decreaseQuantityOf(product: Product) {
        val item = items.find { it.product == product }
        item?.quantity = item?.quantity?.minus(1)!!
    }

    fun clear() {
        items.clear()
    }
}