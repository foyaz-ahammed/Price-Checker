package com.caper.priceapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.caper.priceapp.databinding.ActivityViewBinding
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.helper.formattedPrice
import com.caper.priceapp.viewmodels.PriceItemViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Activity for price item view screen
 */
class PriceViewActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_PRICE_ITEM_ID = "extra_price_item_id"
    }

    private lateinit var binding: ActivityViewBinding
    private lateinit var priceItem: PriceItem
    private val viewModel by viewModel<PriceItemViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemId = intent.getLongExtra(EXTRA_PRICE_ITEM_ID, -1)
        if(itemId == -1L) {
            finish()
            return
        }

        binding.allViews.isVisible = true
        viewModel.priceItem.observe(this) {
            if(it == null) return@observe

            priceItem = it
            updateViews(it)
        }

        viewModel.loadData(itemId)
    }

    /**
     * Update views from price item
     */
    private fun updateViews(item: PriceItem) {
        binding.apply {
            allViews.isVisible = true
            name.text = item.name
            price.text = item.price.formattedPrice()
            Glide.with(this@PriceViewActivity)
                .load(item.thumbnail)
                .into(thumbnail)
            Glide.with(this@PriceViewActivity)
                .load(item.qrUrl)
                .into(qrImage)
        }
    }

    /**
     * Called when edit button is clicked
     */
    fun onEdit(view: View) {}

    /**
     * Called when delete button is clicked
     */
    fun onDelete(view: View) {
        viewModel.deleteItem(priceItem) {
            finish()
        }
    }
}