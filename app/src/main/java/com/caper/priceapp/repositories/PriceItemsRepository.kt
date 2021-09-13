package com.caper.priceapp.repositories

import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.interfaces.PriceDao

/**
 * Repository class for MainViewModel
 */
class PriceItemsRepository(private val priceDao: PriceDao) {
    fun getAllPriceItems() = priceDao.getAllPriceItems()
    fun getPriceItem(id: Long) = priceDao.getPriceItem(id)
    fun deleteItem(item: PriceItem) = priceDao.deleteItem(item)
    suspend fun updateItem(item: PriceItem) = priceDao.updateItem(item)
}