package com.mathbrandino.e_commerce.data

import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.data.local.product.ProductDao
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productDao: ProductDao) {

    suspend fun save(product: Product) {
        productDao.save(product)
    }

    suspend fun delete(product: Product) {
        productDao.delete(product)
    }

    suspend fun update(product: Product) {
        productDao.update(product)
    }

    fun searchAll() =
        productDao.searchAll()

}