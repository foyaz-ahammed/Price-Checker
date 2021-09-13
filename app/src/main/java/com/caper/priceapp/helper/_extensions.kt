package com.caper.priceapp.helper

import com.bumptech.glide.util.Util.isOnMainThread
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

//If callback is on ui thread, run the callback through thread, otherwise run  immediately
fun ensureBackgroundThread(callback: () -> Unit) {
    if (isOnMainThread()) {
        Thread {
            callback()
        }.start()
    } else {
        callback()
    }
}