package com.caper.priceapp.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.interfaces.PriceDao

/**
 * Price Check App database
 */
@Database(entities = [PriceItem::class], version = 1)
abstract class PriceDataBase: RoomDatabase() {
    abstract fun priceDao(): PriceDao
}