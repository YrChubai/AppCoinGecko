package com.example.appcoingecko.data.repository

import com.example.appcoingecko.data.model.CoinCrypto
import com.example.appcoingecko.data.model.CoinCryptoDetail
import com.example.appcoingecko.data.remote.ApiService

class CryptoRepository(private val apiService: ApiService) {

    suspend fun fetchItems(vs: String = "usd"): List<CoinCrypto> {
        return apiService.getItems(vs,30)
    }

    suspend fun fetchItemDetail(id: String): CoinCryptoDetail {
        return apiService.getItemDetail(id)
    }
}