package com.caper.priceapp.repositories

import com.caper.priceapp.interfaces.PriceDao

/**
 * Repository class for MainViewModel
 */
class PriceItemsRepository(private val priceDao: PriceDao) {
    fun getAllPriceItems() = priceDao.getAllPriceItems()
}