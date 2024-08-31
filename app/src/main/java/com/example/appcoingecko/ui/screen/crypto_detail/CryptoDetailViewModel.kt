package com.example.appcoingecko.ui.screen.crypto_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcoingecko.data.repository.CryptoRepository
import com.example.appcoingecko.data.model.CoinCryptoDetail
import com.example.appcoingecko.data.model.CoinCryptoDetailModif
import com.example.appcoingecko.data.remote.AdapterApi

import kotlinx.coroutines.launch

class CryptoDetailViewModel : ViewModel(){
    private val apiService = AdapterApi.create()
    private val repository = CryptoRepository(apiService)

    private val _state = mutableStateOf(CryptoDetailState())
    val state: State<CryptoDetailState> = _state

    fun loadItems(id: String) {
        viewModelScope.launch {
            try {
                _state.value = CryptoDetailState(isLoading = true)

                val call = repository.fetchItemDetail(id)
                val result = formateRespone(call)

                _state.value = CryptoDetailState(coin = result)
                Log.d("Loadeddetail", _state.value.toString())

            } catch (e: Exception){
                _state.value = CryptoDetailState(
                    error = e.message ?: "An unexpected error occured"
                )
                Log.d("ERRORdetail", e.toString())
            }
        }
    }
    fun formateRespone(responsetItem: CoinCryptoDetail) : CoinCryptoDetailModif {

        val cleanedString = responsetItem.description["en"]!!.replace("\r\n", "\n")

        val aTagPattern = Regex("<a[^>]*>.</a>", RegexOption.DOT_MATCHES_ALL)
        val withoutATags = cleanedString.replace(aTagPattern, "")

        val htmlPattern = Regex("<[^>]*>")
        val withoutHtmlTags = withoutATags.replace(htmlPattern, "")

        val urlPattern = Regex("https?://\\S+")
        val finalText = withoutHtmlTags.replace(urlPattern, "")

        val urlString = responsetItem.image["large"]!!
        val coinModif =
                CoinCryptoDetailModif(
                    responsetItem.id,
                    responsetItem.name,
                    finalText,
                    responsetItem.categories,
                    urlString
                )
        return coinModif
    }

}