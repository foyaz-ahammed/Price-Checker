package com.caper.priceapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.repositories.PriceItemsRepository
import kotlinx.coroutines.launch

/**
 * [ViewModel] class for main screen
 */
class MainViewModel(private val repository: PriceItemsRepository): ViewModel() {
    private val _priceItems = repository.getAllPriceItems()

    val priceItems: LiveData<List<PriceItem>>
        get() = _priceItems

    fun updateItem(item: PriceItem) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }
}