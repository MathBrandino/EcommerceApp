package com.mathbrandino.e_commerce.domain.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.mathbrandino.e_commerce.data.local.product.Product
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Cart @Inject constructor() {

    var items = mutableStateListOf<CartItem>()

    var total = mutableStateOf(BigDecimal.ZERO)
        private set

    fun add(product: Product) {
        val item = items.find { it.product == product }
        if (item == null)
            items.add(CartItem(product, 1))
        else
            increaseQuantityOf(product)
        atualiza()
    }

    fun increaseQuantityOf(product: Product) {
        val item = items.find { it.product == product }
        item?.quantity = item?.quantity?.plus(1)!!
        atualiza()
    }

    fun decreaseQuantityOf(product: Product): Boolean {
        val item = items.find { it.product == product }
        item?.quantity = item?.quantity?.minus(1)!!
        atualiza()
        if (item.quantity == 0) {
            return items.remove(items.find { it.product == product })
        }
        return false
    }

    fun clear() {
        items.clear()
        atualiza()
    }

    private fun atualiza() {
        total.value = items.sumOf { it.getTotal() }
    }
}