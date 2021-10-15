package com.mathbrandino.e_commerce.domain.useCases

import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.domain.model.Cart
import javax.inject.Inject

class AddProductCartUseCase @Inject constructor(private val cart: Cart) {

    fun add(product: Product) {
        cart.add(product)
    }
}