package com.caper.priceapp.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.caper.priceapp.entities.PriceItem

/**
 * Dao Class for price table
 */
@Dao
interface PriceDao {
    @Query("SELECT * FROM TB_PRICE_ITEM")
    fun getAllPriceItems(): LiveData<List<PriceItem>>

    @Query("SELECT * FROM TB_PRICE_ITEM where id == :itemId")
    suspend fun getPriceItem(itemId: Long): PriceItem
}