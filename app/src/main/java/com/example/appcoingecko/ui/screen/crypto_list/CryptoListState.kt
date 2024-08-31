package com.example.appcoingecko.ui.screen.crypto_list

import com.example.appcoingecko.data.model.CoinCryptoModif

data class CryptoListState(
    val isLoading: Boolean = false,
    val coins: List<CoinCryptoModif> = emptyList(),
    val error: String = "",
    val errorRefresh: String = ""

)