package com.example.appcoingecko.ui.screen.crypto_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcoingecko.ui.screen.crypto_list.CryptoListViewModel
import com.example.appcoingecko.ui.theme.CryptoGray
import com.example.appcoingecko.ui.theme.SecondOrange

@Composable
fun CurrencyFilterChip(
    selectedChip: CryptoListViewModel.CurrencyOption,
    currencyOption: CryptoListViewModel.CurrencyOption,
    onSelected: (CryptoListViewModel.CurrencyOption) -> Unit,
    label: String
) {
    FilterChip(
        selected = selectedChip == currencyOption,
        onClick = { onSelected(currencyOption) },
        label = {
            Row(
                modifier = Modifier
                    .width(60.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(label, fontSize = 14.sp)
            }
        },
        modifier = Modifier
            .padding(end = 10.dp)
            .height(35.dp),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = SecondOrange.copy(alpha = 0.12f),
            selectedLabelColor = SecondOrange,
            containerColor = CryptoGray,
            labelColor = Color.Black
        ),
        border = null,
        shape = RoundedCornerShape(18.dp)
    )
}