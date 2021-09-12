package com.caper.priceapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.repositories.PriceItemsRepository
import kotlinx.coroutines.launch

/**
 * [ViewModel] class for view screen
 */
class PriceItemViewModel(private val repository: PriceItemsRepository): ViewModel() {
    private val _priceItem = MutableLiveData<PriceItem>()
    val priceItem: LiveData<PriceItem>
        get() = _priceItem

    fun loadData(id: Long) {
        viewModelScope.launch {
            val result = repository.getPriceItem(id)
            _priceItem.value = result
        }
    }

    fun deleteItem(item: PriceItem, callback: () -> Unit) {
        viewModelScope.launch {
            repository.deleteItem(item)
            callback.invoke()
        }
    }
}