package com.example.appcoingecko.ui.screen.crypto_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appcoingecko.ui.screen.componets.CircularLoader
import com.example.appcoingecko.ui.screen.componets.ErrorRefresher
import com.example.appcoingecko.ui.screen.crypto_list.components.ChipsCurrency
import com.example.appcoingecko.ui.screen.crypto_list.components.CryptoListItem


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CryptoListScreen(navController: NavController, viewModel: CryptoListViewModel = viewModel()) {
    val selectedChip by remember { viewModel.liveChipsSelected }

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(117.dp)
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color.Black,
                    )
                ,color = Color.White,
                shadowElevation = 5.dp
            ) {
                Column(
                    modifier = Modifier.padding(start=16.dp, top = 16.dp)
                ) {
                    Text(
                        text = "Список криптовалют",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                    )
                    Row (modifier = Modifier.padding(top = 16.dp)){
                        ChipsCurrency()
                    }
                }
            }
        },
        content = { paddingValues ->
            PullToRefreshBox(
                isRefreshing = viewModel.isRefreshing,
                onRefresh = {
                        viewModel.refreshItems(selectedChip.name)
                },
                modifier = Modifier.padding(paddingValues)
            ){
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        itemsIndexed(
                            state.coins
                        ) { _, item ->
                            CryptoListItem(item, onItemClick = {
                                navController.navigate("crypto_detail/${item.id}")
                            })
                        }
                    }
                    if(state.isLoading) {
                        CircularLoader()
                    }
                    if(state.error.isNotBlank()) {
                        ErrorRefresher {
                            viewModel.loadItems(selectedChip.name)
                        }
                    }
            }
        }
    )
}




