package com.example.appcoingecko.ui.screen.crypto_list


import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcoingecko.data.repository.CryptoRepository
import com.example.appcoingecko.ui.theme.GreenStock
import com.example.appcoingecko.ui.theme.RedStock
import com.example.appcoingecko.data.model.CoinCrypto
import com.example.appcoingecko.data.model.CoinCryptoModif
import com.example.appcoingecko.data.remote.AdapterApi
import kotlinx.coroutines.launch
import java.text.DecimalFormat


class CryptoListViewModel : ViewModel() {
    private val apiService = AdapterApi.create()
    private val repository = CryptoRepository(apiService)

    private val _state = mutableStateOf(CryptoListState())
    val state: State<CryptoListState> = _state

    var isRefreshing by mutableStateOf(false)

    enum class CurrencyOption { usd, rub }

    var liveChipsSelected = mutableStateOf(CurrencyOption.usd)

    init {
        loadItems("usd")
    }


    fun loadItems(currency: String, refresh: Boolean = false) {
        viewModelScope.launch {
            try {
                if (refresh){isRefreshing = true}
                _state.value = CryptoListState(isLoading = true)

                val call = repository.fetchItems(currency)
                val result = formateRespone(call, currency)

                _state.value = CryptoListState(coins = result)

            } catch (e: Exception) {
                _state.value = CryptoListState(
                    error = e.message ?: "An unexpected error occured"
                )
            }
            finally {
                if (refresh){
                isRefreshing = false}
            }
        }
    }

    fun refreshItems(currency: String) {
        try {
            loadItems(currency, true)
        } catch (e: Exception) {
        }
    }

    fun formateRespone(responsetItem: List<CoinCrypto>, currency: String): List<CoinCryptoModif> {
        val coinModif =
            responsetItem.mapIndexed { _, item ->
                CoinCryptoModif(
                    item.id,
                    item.name,
                    item.symbol.uppercase(),
                    item.image,
                    formatePrice(item.current_price, currency),
                    formatePriceChange(item.price_change_percentage_24h)
                )
            }
        return coinModif
    }

    fun formatePrice(price: Double, currency: String): String {
        val roundedString = String.format("%,.2f", price)
        return if (currency == "usd") "$ $roundedString" else "₽ $roundedString"
    }

    fun formatePriceChange(priceChange: Double): String {
        val decimalFormat = DecimalFormat("0.00")
        val formattedValue = decimalFormat.format(priceChange)
        return if (priceChange >= 0) {
            "+ $formattedValue%"
        } else {
            "- ${formattedValue.substring(1)}%"
        }
    }

    fun getColorPriceChange(priceChange: String): Color {
        return if (priceChange.first() == '+') {
            GreenStock
        } else {
            RedStock
        }
    }

}
