package com.caper.priceapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.caper.priceapp.databinding.ActivityEditBinding
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.interfaces.PriceDao
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

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
    private val priceDao: PriceDao by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener {
            submit()
        }

        if(savedInstanceState != null) return

        isAdd = intent.getBooleanExtra(EXTRA_ADD_OR_EDIT, true)
        val priceItem: PriceItem =
            if(isAdd) { //Add
                PriceItem(0, "", "", "", 0f)
            } else { //Edit
                intent.getParcelableExtra<PriceItem>(EXTRA_PRICE_ITEM) as PriceItem
            }

        updateViews(priceItem)
    }

    /**
     * Update views from [PriceItem]
     */
    private fun updateViews(priceItem: PriceItem) {
        binding.apply {
            name.setText(priceItem.name)
            price.setText(priceItem.price.toString())
            thumbnail.setText(priceItem.thumbnail)
            qrImage.setText(priceItem.qrUrl)
        }
    }

    /**
     * Called when "add" button is clicked
     */
    private fun submit() {
        if(isAdd) {
            val name = binding.name.text.toString()
            val price = binding.price.text.toString()
            val thumbnail = binding.thumbnail.text.toString()
            val qrImage = binding.qrImage.text.toString()

            if(name.isEmpty() || price.isEmpty() || thumbnail.isEmpty() || qrImage.isEmpty()) {
                Toast.makeText(this, "Every field is required to be filled", Toast.LENGTH_SHORT).show()
                return
            }

            val item = PriceItem(0,qrImage, thumbnail, name, price.toFloat())

            runBlocking {
                withContext(Dispatchers.IO) {
                    priceDao.insertItem(item)
                    finish()
                }
            }
        }
    }
}