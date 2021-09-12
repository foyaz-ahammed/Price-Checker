package com.caper.priceapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caper.priceapp.R
import com.caper.priceapp.adapters.PriceItemListAdapter
import com.caper.priceapp.databinding.ActivityMainBinding
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.helper.getTotalPrice

/**
 * Activity for main screen
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = PriceItemListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.priceRecyclerview.adapter = adapter

        val priceList = listOf(
            PriceItem("0001", "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0001", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/banana_1f34c.png", "Banana", "$1.00"),
            PriceItem("0002", "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0002", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/red-apple_1f34e.png", "Apple", "$4.00"),
            PriceItem("0003", "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0003", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/sparkles_2728.png", "Other Stuff", "$10.00"),
        )
        updateViews(priceList)
    }

    /**
     * Update views from price list
     */
    private fun updateViews(priceList: List<PriceItem>) {
        adapter.submitList(priceList)
        binding.total.text = getString(R.string.total_price_format, priceList.size, priceList.getTotalPrice())
    }
}