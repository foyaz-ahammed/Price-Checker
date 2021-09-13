package com.caper.priceapp.modules

import android.app.Application
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.caper.priceapp.databases.PriceDataBase
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.helper.DB_NAME
import com.caper.priceapp.interfaces.PriceDao
import com.caper.priceapp.repositories.PriceItemsRepository
import com.caper.priceapp.viewmodels.MainViewModel
import com.caper.priceapp.viewmodels.PriceItemViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/* Define koin module variables */
val databaseModule = module {
    single { provideDatabase(androidApplication())}
    single { providePriceDao(get()) }
}

val repositoryModule = module {
    single { PriceItemsRepository(get()) }
}

val viewModelModule = module {
    single { MainViewModel(get()) }
    single { PriceItemViewModel(get()) }
}

/**
 * @return [PriceDataBase] instance
 */
fun provideDatabase(application: Application) =
    Room.databaseBuilder(application, PriceDataBase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .addCallback(
            object : RoomDatabase.Callback() {
                /**
                 * Insert default data on first run
                 */
                override fun onCreate(db: SupportSQLiteDatabase) {
                    insertPriceItem(db, PriceItem(1, "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0001", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/banana_1f34c.png", "Banana", 1f))
                    insertPriceItem(db, PriceItem(2, "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0002", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/red-apple_1f34e.png", "Apple", 4f))
                    insertPriceItem(db, PriceItem(3, "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0003", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/sparkles_2728.png", "Other Stuff", 10f))
                }

                /**
                 * Insert [PriceItem] to the table
                 */
                fun insertPriceItem(db: SupportSQLiteDatabase, item: PriceItem) {
                    val contentValues = ContentValues().apply {
                        put("id", item.id)
                        put("qrUrl", item.qrUrl)
                        put("thumbnail", item.thumbnail)
                        put("name", item.name)
                        put("price", item.price)
                        put("favorite", item.favorite)
                    }

                    db.insert("TB_PRICE_ITEM", SQLiteDatabase.CONFLICT_REPLACE, contentValues)
                }
            }
        )
        .build()

/**
 * @return [PriceDao] instance
 */
fun providePriceDao(priceDb: PriceDataBase): PriceDao = priceDb.priceDao()