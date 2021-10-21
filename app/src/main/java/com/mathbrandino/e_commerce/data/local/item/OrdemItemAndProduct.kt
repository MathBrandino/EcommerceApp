package com.mathbrandino.e_commerce.data.local.item

import androidx.room.Embedded
import androidx.room.Relation
import com.mathbrandino.e_commerce.data.local.product.Product

data class OrdemItemAndProduct(
    @Embedded val orderItem: OrderItem,
    @Relation(
        parentColumn = "product_id",
        entityColumn = "id_product",
    )
    val product: Product
)
