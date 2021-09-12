package com.caper.priceapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.repositories.PriceItemsRepository

/**
 * [ViewModel] class for main screen
 */
class MainViewModel(repository: PriceItemsRepository): ViewModel() {
    private val _priceItems = repository.getAllPriceItems()

    val priceItems: LiveData<List<PriceItem>>
        get() = _priceItems
}