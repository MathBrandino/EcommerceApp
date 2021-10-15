package com.mathbrandino.e_commerce.data.local.product

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Product(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable
