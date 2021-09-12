package com.caper.priceapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caper.priceapp.databinding.ActivityEditBinding
import com.caper.priceapp.entities.PriceItem

/**
 * Activity for screen when adding or editing a price item
 */
class PriceEditActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_ADD_OR_EDIT = "extra_add_or_edit"
        const val EXTRA_PRICE_ITEM = "extra_price_item"
    }

    private lateinit var binding: ActivityEditBinding
    private var isAdd: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isAdd = intent.getBooleanExtra(EXTRA_ADD_OR_EDIT, true)
        val priceItem: PriceItem =
            if(isAdd) { //Add
                PriceItem(0, "", "", "", "")
            } else { //Edit
                intent.getParcelableExtra<PriceItem>(EXTRA_PRICE_ITEM) as PriceItem
            }

        updateViews(priceItem)
    }

    private fun updateViews(priceItem: PriceItem) {
    }
}