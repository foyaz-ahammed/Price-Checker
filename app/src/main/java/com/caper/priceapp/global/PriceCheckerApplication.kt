package com.caper.priceapp.global

import android.app.Application
import com.caper.priceapp.modules.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class for this app
 */
class PriceCheckerApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PriceCheckerApplication)
            modules(databaseModule)
        }
    }
}