package com.caper.priceapp.view

import android.content.Context
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.caper.priceapp.R
import com.caper.priceapp.activities.PriceEditActivity
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.extension.hasText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EditActivityTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun check_Submit_Button_Text() {
        runAddActivity()
        R.id.submit.hasText(appContext().getString(R.string.add))

        runEditActivity(PriceItem(0L, "", "", "", 10f))
        R.id.submit.hasText(appContext().getString(R.string.save))
    }

    @Test
    fun check_views_text() {
        val item = PriceItem(3L, "https://qrcode", "https://thumb", "banana", 3.45f)
        runEditActivity(item)

        R.id.name.hasText(item.name)
        R.id.price.hasText(item.price.toString())
        R.id.thumbnail.hasText(item.thumbnail)
        R.id.qr_image.hasText(item.qrUrl)
    }

    private fun runAddActivity() {
        ActivityScenario.launch<PriceEditActivity>(
            Intent(ApplicationProvider.getApplicationContext(), PriceEditActivity::class.java).apply {
                putExtra(PriceEditActivity.EXTRA_ADD_OR_EDIT, true)
            }
        )
    }

    private fun runEditActivity(item: PriceItem) {
        ActivityScenario.launch<PriceEditActivity>(
            Intent(ApplicationProvider.getApplicationContext(), PriceEditActivity::class.java).apply {
                putExtra(PriceEditActivity.EXTRA_ADD_OR_EDIT, false)
                putExtra(PriceEditActivity.EXTRA_PRICE_ITEM, item)
            }
        )
    }

    private fun appContext(): Context = InstrumentationRegistry.getInstrumentation().targetContext
}