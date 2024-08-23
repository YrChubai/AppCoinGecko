package com.example.traineapp.data.remote

import com.example.traineapp.data.model.CoinCrypto
import com.example.traineapp.data.model.CoinCryptoDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("coins/markets")
    suspend fun getItems(@Query("vs_currency") vs_currency: String, @Query("per_page") per_page: Int): List<CoinCrypto>

    @GET("coins/{id}")
    suspend fun getItemDetail(@Path("id") id: String): CoinCryptoDetail
}

