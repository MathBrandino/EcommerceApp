package com.mathbrandino.e_commerce.data.local.product

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert
    suspend fun save(product: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("select * from Product")
    fun searchAll(): Flow<List<Product>>
}