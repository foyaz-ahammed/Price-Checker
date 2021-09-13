package com.caper.priceapp.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.caper.priceapp.entities.PriceItem

/**
 * Dao Class for price table
 */
@Dao
interface PriceDao {
    @Query("SELECT * FROM TB_PRICE_ITEM")
    fun getAllPriceItems(): LiveData<List<PriceItem>>

    @Query("SELECT * FROM TB_PRICE_ITEM where id = :itemId")
    fun getPriceItem(itemId: Long): PriceItem

    @Delete
    fun deleteItem(item: PriceItem)

    @Insert
    suspend fun insertItem(item: PriceItem)

    @Update
    suspend fun updateItem(item: PriceItem)
}