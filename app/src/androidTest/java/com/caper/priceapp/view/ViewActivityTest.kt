package com.caper.priceapp.view

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.caper.priceapp.R
import com.caper.priceapp.activities.PriceViewActivity
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.extension.hasText
import com.caper.priceapp.extension.isDisplayed
import com.caper.priceapp.helper.formattedPrice
import com.caper.priceapp.repositories.PriceItemsRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class ViewActivityTest {
    private val repository = mockk<PriceItemsRepository>(relaxed = true)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        loadKoinModules(module {
            single { repository }
        })

    }

    @Test
    fun check_Views_Displayed() {
        val item = PriceItem(0, "", "", "name", 0f)
        providePriceItem(item)

        runActivity()

        R.id.thumbnail.isDisplayed()
        R.id.name_label.isDisplayed()
        R.id.name.isDisplayed()
        R.id.price_label.isDisplayed()
        R.id.price.isDisplayed()
        R.id.qr_code_description.isDisplayed()
        R.id.qr_image.isDisplayed()
    }

    @Test
    fun check_name_price_text() {
        val item = PriceItem(0, "", "", "banana", 12f)
        providePriceItem(item)

        runActivity()
        R.id.name.hasText(item.name)
        R.id.price.hasText(item.price.formattedPrice())
    }

    private fun providePriceItem(item: PriceItem) {
        every { repository.getPriceItem(any()) } returns item
    }

    private fun runActivity() {
        ActivityScenario.launch<PriceViewActivity>(
            Intent(ApplicationProvider.getApplicationContext(), PriceViewActivity::class.java).apply {
                putExtra(PriceViewActivity.EXTRA_PRICE_ITEM_ID, 1L)
            }
        )
    }
}