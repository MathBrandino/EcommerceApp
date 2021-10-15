package com.mathbrandino.e_commerce.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mathbrandino.e_commerce.data.local.item.OrderItem
import com.mathbrandino.e_commerce.data.local.item.OrderItemDao
import com.mathbrandino.e_commerce.data.local.order.Order
import com.mathbrandino.e_commerce.data.local.order.OrderDao
import com.mathbrandino.e_commerce.data.local.product.Product
import com.mathbrandino.e_commerce.data.local.product.ProductDao

@Database(entities = [Product::class, Order::class, OrderItem::class], version = 1)
abstract class EcommerceDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getOrderDao(): OrderDao
    abstract fun getOrderItemDao(): OrderItemDao

    companion object {
        private var database: EcommerceDatabase? = null

        fun getDatabase(context: Context): EcommerceDatabase {
            return database ?: createDatabase(context).also { database = it }
        }

        private fun createDatabase(context: Context): EcommerceDatabase {
            return Room.databaseBuilder(context, EcommerceDatabase::class.java, "EcommerceDB")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}