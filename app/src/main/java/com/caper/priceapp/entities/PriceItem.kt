package com.caper.priceapp.entities

/**
 * Price Item model class
 */
data class PriceItem(
    val id: String,
    val qrUrl: String,
    val thumbnail: String,
    val name: String,
    val price: String
)