package com.caper.priceapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.caper.priceapp.R
import com.caper.priceapp.adapters.PriceItemListAdapter
import com.caper.priceapp.databinding.ActivityMainBinding
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.helper.getTotalPrice
import com.caper.priceapp.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Activity for main screen
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private val adapter = PriceItemListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup adapter
        binding.priceRecyclerview.adapter = adapter

        //Setup add button click listener
        binding.add.setOnClickListener {
            val intent = Intent(this, PriceEditActivity::class.java)
            startActivity(intent)
        }
        
        //Observe data
        viewModel.priceItems.observe(this) {
            Log.w("MainActivity", it.toString())
        }

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