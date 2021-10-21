package com.mathbrandino.e_commerce.data.local.order

import androidx.room.Embedded
import androidx.room.Relation
import com.mathbrandino.e_commerce.data.local.item.OrdemItemAndProduct
import com.mathbrandino.e_commerce.data.local.item.OrderItem

data class OrderWithItems(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "id_order",
        entityColumn = "order_id",
        entity = OrderItem::class
    )
    val items : List<OrdemItemAndProduct>
)
