package com.caper.priceapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caper.priceapp.databinding.ActivityViewBinding

class PriceViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}