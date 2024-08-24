package com.example.traineapp.ui.screen.crypto_list

import com.example.traineapp.data.model.CoinCryptoModif

data class CryptoListState(
    val isLoading: Boolean = false,
    val coins: List<CoinCryptoModif> = emptyList(),
    val error: String = "",
    val errorRefresh: Boolean = false
)