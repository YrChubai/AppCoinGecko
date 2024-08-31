package com.example.appcoingecko.data.model

data class CoinCrypto(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val current_price: Double,
    val price_change_percentage_24h: Double,
)
