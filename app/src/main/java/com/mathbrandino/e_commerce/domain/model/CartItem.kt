package com.mathbrandino.e_commerce.domain.model

import com.mathbrandino.e_commerce.data.local.product.Product
import java.math.BigDecimal
import java.math.RoundingMode

data class CartItem(
    val product: Product,
    var quantity: Int
) {
    fun getTotal(): BigDecimal = BigDecimal.valueOf(product.value).multiply(quantity.toBigDecimal())
        .setScale(2, RoundingMode.HALF_UP)
}
