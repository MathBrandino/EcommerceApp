package com.mathbrandino.e_commerce.data.local.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mathbrandino.e_commerce.data.local.order.Order
import com.mathbrandino.e_commerce.data.local.product.Product

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            childColumns = ["order_id"],
            parentColumns = ["id_order"]
        ), ForeignKey(
            entity = Product::class,
            childColumns = ["product_id"],
            parentColumns = ["id_product"]
        )
    ]
)
data class OrderItem(
    @ColumnInfo(name = "id_order_item") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "product_id") val produtoId: Int,
    @ColumnInfo(name = "order_id") val orderId: Int,
    @ColumnInfo(name = "item_count") val quantity: Int
)