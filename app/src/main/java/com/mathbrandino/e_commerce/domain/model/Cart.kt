package com.mathbrandino.e_commerce.domain.model

import com.mathbrandino.e_commerce.data.local.product.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Cart @Inject constructor() {

    val items = ArrayList<CartItem>()

    fun getTotal() = items.sumOf { it.getTotal() }

    fun add(product: Product) {
        val item = items.find { it.product == product }
        if (item == null)
            items.add(CartItem(product, 1))
        else
            increaseQuantityOf(product)
    }

    fun increaseQuantityOf(product: Product) {
        val item = items.find { it.product == product }
        item?.quantity = item?.quantity?.plus(1)!!
    }

    fun decreaseQuantityOf(product: Product): Boolean {
        val item = items.find { it.product == product }
        item?.quantity = item?.quantity?.minus(1)!!
        if (item.quantity == 0) {
            return items.remove(items.find { it.product == product })
        }
        return false
    }

    fun clear() {
        items.clear()
    }
}