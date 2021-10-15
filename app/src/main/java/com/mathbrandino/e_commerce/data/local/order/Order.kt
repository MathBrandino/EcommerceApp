package com.mathbrandino.e_commerce.data.local.order

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order(
    @ColumnInfo(name = "id_order") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "total") val total: Double
)
