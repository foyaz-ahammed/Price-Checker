package com.caper.priceapp.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.caper.priceapp.R
import com.caper.priceapp.activities.MainActivity
import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.extension.isDisplayed
import com.caper.priceapp.extension.isNotDisplayed
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
class MainActivityTest {
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
    fun check_NoItemView_IsDisplayed() {
        provideEmptyTable()
        ActivityScenario.launch(MainActivity::class.java)

        R.id.price_recyclerview.isNotDisplayed()
        R.id.no_item_view.isDisplayed()
    }

    @Test
    fun check_testContentRecyclerView_IsDisplayed() {
        provideSomeDataTable()
        ActivityScenario.launch(MainActivity::class.java)

        R.id.price_recyclerview.isDisplayed()
        R.id.no_item_view.isNotDisplayed()
    }

    private fun provideEmptyTable() {
        val data = MutableLiveData<List<PriceItem>>()
        data.value = emptyList()
        every { repository.getAllPriceItems() } returns data
    }

    private fun provideSomeDataTable() {
        val data = MutableLiveData<List<PriceItem>>()
        data.value = listOf(
            PriceItem(1, "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0001", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/banana_1f34c.png", "Banana", 1f),
            PriceItem(2, "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0002", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/red-apple_1f34e.png", "Apple", 4f),
            PriceItem(3, "https://zxing.org/w/chart?cht=qr&chs=350x350&chld=L&choe=UTF-8&chl=0003", "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/120/apple/237/sparkles_2728.png", "Other Stuff", 10f)
        )
        every { repository.getAllPriceItems() } returns data
    }
}