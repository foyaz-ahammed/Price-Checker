package com.caper.priceapp.helper

import com.caper.priceapp.entities.PriceItem

/* Define extension functions */

/**
 * Get price amount ("$100.0" -> 100.0)
 */
fun PriceItem.getPriceAmount(): Float {
    if(price.isEmpty()) {
        return 0f
    }

    val numberString = if(price.first() == '$') price.substring(1) else price
    return numberString.toFloatOrNull()?:0f
}

/**
 * Get total price
 */
fun List<PriceItem>.getTotalPrice(): Float {
    var sum = 0f
    forEach { sum += it.getPriceAmount() }

    return sum
}