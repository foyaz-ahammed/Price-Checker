package com.caper.priceapp

import com.caper.priceapp.entities.PriceItem
import com.caper.priceapp.helper.formattedPrice
import com.caper.priceapp.helper.getTotalPrice
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

class HelperFunctionTest {
    @Test
    fun formattedPrice_function_test() {
        assertEquals(3.45f.formattedPrice(), "$3.45")
        assertEquals(3.451f.formattedPrice(), "$3.45")
        assertEquals(3.457f.formattedPrice(), "$3.46")
        assertEquals(3f.formattedPrice(), "$3.00")
    }

    @Test
    fun getTotalPrice_function_test() {
        assertEquals(getTotalPrice(), 0f)
        assertEquals(getTotalPrice(3f), 3f)
        assertEquals(getTotalPrice(3.4f, 3.6f), 7f)
        assertEquals(getTotalPrice(1f, 2f, 3f, 4f), 10f)
    }

    private fun getTotalPrice(vararg prices: Float): Float {
        val priceItems = ArrayList<PriceItem>()
        prices.forEach {
            priceItems.add(PriceItem(Random.nextLong(), "", "", "", it))
        }

        return priceItems.getTotalPrice()
    }
}