package com.mathbrandino.e_commerce.data.local.order

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Insert
    suspend fun create(order: Order) : Long

    @Transaction
    @Query("select * from `Order`")
    fun searchAll(): Flow<List<OrderWithItems>>
}