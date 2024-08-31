package com.example.appcoingecko.ui.screen.crypto_detail

import com.example.appcoingecko.data.model.CoinCryptoDetailModif

data class CryptoDetailState(
    val isLoading: Boolean = false,
    val coin: CoinCryptoDetailModif? = null,
    val error: String = ""
)