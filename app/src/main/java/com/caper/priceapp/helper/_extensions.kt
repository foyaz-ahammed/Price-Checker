package com.caper.priceapp.helper

import com.caper.priceapp.entities.PriceItem

/* Define extension functions */

/**
 * Get total price
 */
fun List<PriceItem>.getTotalPrice(): Float {
    var sum = 0f
    forEach { sum += it.price }

    return sum
}

/**
 * Get price string(10.00 ~ $10.00)
 */
fun Float.formattedPrice(): String = String.format("$%.2f", this)