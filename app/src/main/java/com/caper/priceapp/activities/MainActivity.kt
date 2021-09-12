package com.caper.priceapp.activities

import android.content.Intent
import android.os.Bundle
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
            val intent = Intent(this, PriceEditActivity::class.java).apply {
                putExtra(PriceEditActivity.EXTRA_ADD_OR_EDIT, true)
            }
            startActivity(intent)
        }

        //Observe data
        viewModel.priceItems.observe(this) {
            updateViews(it)
        }
    }

    /**
     * Update views from price list
     */
    private fun updateViews(priceList: List<PriceItem>) {
        adapter.submitList(priceList)
        binding.total.text = getString(R.string.total_price_format, priceList.size, priceList.getTotalPrice())
    }
}