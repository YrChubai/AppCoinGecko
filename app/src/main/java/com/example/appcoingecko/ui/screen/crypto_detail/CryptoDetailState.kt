package com.example.traineapp.ui.screen.crypto_detail

import com.example.traineapp.data.model.CoinCryptoDetailModif

data class CryptoDetailState(
    val isLoading: Boolean = false,
    val coin: CoinCryptoDetailModif? = null,
    val error: String = ""
)