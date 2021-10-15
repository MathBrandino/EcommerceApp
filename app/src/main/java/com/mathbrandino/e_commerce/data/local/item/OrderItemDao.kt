package com.mathbrandino.e_commerce.data.local.item

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface OrderItemDao {

    @Insert
    suspend fun create(order: OrderItem)

}