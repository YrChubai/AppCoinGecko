package com.example.appcoingecko.data.model

data class CoinCryptoModif(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val current_price: String,
    val price_change_percentage_24h: String,
)

