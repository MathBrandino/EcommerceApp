package com.mathbrandino.e_commerce.domain.useCases

import com.mathbrandino.e_commerce.data.local.order.OrderDao
import javax.inject.Inject

class SearchOrderUseCase @Inject constructor(private val orderDao: OrderDao) {

    fun search() = orderDao.searchAll()
}