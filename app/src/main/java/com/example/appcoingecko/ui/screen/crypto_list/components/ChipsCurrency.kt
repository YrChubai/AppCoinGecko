package com.example.appcoingecko.ui.screen.crypto_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appcoingecko.ui.screen.crypto_list.CryptoListViewModel

@Composable
fun ChipsCurrency(viewModel: CryptoListViewModel = viewModel()){
    var selectedChip by remember { viewModel.liveChipsSelected }

    Column {
        Row {
            CurrencyFilterChip(
                selectedChip = selectedChip,
                currencyOption = CryptoListViewModel.CurrencyOption.usd,
                onSelected = {
                    selectedChip = it
                    viewModel.loadItems("usd")
                },
                label = "USD"
            )
            CurrencyFilterChip(
                selectedChip = selectedChip,
                currencyOption = CryptoListViewModel.CurrencyOption.rub,
                onSelected = {
                    selectedChip = it
                    viewModel.loadItems("rub")
                },
                label = "RUB"
            )
        }
    }
}