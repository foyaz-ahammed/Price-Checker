package com.caper.priceapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Price Item model class
 */
@Entity(
    tableName = "TB_PRICE_ITEM"
)
data class PriceItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "qrUrl") val qrUrl: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String
)