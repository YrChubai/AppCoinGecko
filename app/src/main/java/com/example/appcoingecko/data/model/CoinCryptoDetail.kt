package com.example.appcoingecko.data.model

data class CoinCryptoDetail(
    val id: String,
    val name: String,
    val description: Map<String, String>,
    val categories: List<String>,
    val image: Map<String, String>
)
