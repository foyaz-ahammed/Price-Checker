package com.caper.priceapp.interfaces

import androidx.room.Dao
import androidx.room.Query
import com.caper.priceapp.entities.PriceItem

/**
 * Dao Class for price table
 */
@Dao
interface PriceDao {
    @Query("SELECT * FROM TB_PRICE_ITEM")
    fun getAllPriceItems(): List<PriceItem>
}