package com.mathbrandino.e_commerce.domain.useCases

import com.mathbrandino.e_commerce.data.ProductRepository
import com.mathbrandino.e_commerce.data.local.product.Product
import javax.inject.Inject

class SaveProdutcUseCase @Inject constructor(private val repository: ProductRepository) {

    suspend fun save(product: Product) = if (product.id == 0) {
        repository.save(product)
    } else {
        repository.update(product)
    }
}
