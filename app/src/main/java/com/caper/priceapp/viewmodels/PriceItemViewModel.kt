package com.caper.priceapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.helper.ensureBackgroundThread
import com.caper.priceapp.repositories.PriceItemsRepository

/**
 * [ViewModel] class for view screen
 */
class PriceItemViewModel(private val repository: PriceItemsRepository): ViewModel() {
    private val _priceItem = MutableLiveData<PriceItem>()
    val priceItem: LiveData<PriceItem>
        get() = _priceItem

    fun loadData(id: Long) {
        ensureBackgroundThread {
            val result = repository.getPriceItem(id)
            _priceItem.postValue(result)
        }
    }

    fun deleteItem(item: PriceItem, callback: () -> Unit) {
        ensureBackgroundThread {
            repository.deleteItem(item)
            callback.invoke()
        }
    }
}