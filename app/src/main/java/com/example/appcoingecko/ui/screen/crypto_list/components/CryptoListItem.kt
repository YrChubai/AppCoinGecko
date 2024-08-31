package com.example.appcoingecko.ui.screen.crypto_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.appcoingecko.data.model.CoinCryptoModif
import com.example.appcoingecko.ui.screen.crypto_list.CryptoListViewModel


@Composable
fun CryptoListItem(
    coin: CoinCryptoModif,
    onItemClick: (CoinCryptoModif) -> Unit = {},
    viewModel: CryptoListViewModel = viewModel()
) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(coin)}
        .padding(top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
            ){
                AsyncImage(
                    model = coin.image,
                    contentDescription = "icon crypto coin",
                )
            }
            Column (
                modifier = Modifier.padding(start = 8.dp)
            ){
                Text(
                    text = coin.name,
                )

                Text(
                    text = coin.symbol, fontSize = 12.sp
                )
            }
        }
        Row (modifier = Modifier
            .padding(end = 8.dp),

        ){
            Column(
                modifier = Modifier
                    .padding(),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = coin.current_price,
                )
                Text(
                    text = coin.price_change_percentage_24h,
                    color = viewModel.getColorPriceChange(coin.price_change_percentage_24h)
                )
            }
        }
    }

}
