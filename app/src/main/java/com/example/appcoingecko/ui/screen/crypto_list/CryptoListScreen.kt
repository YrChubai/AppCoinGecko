package com.example.traineapp.ui.screen.crypto_list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appcoingecko.ui.theme.CryptoGray
import com.example.appcoingecko.ui.theme.DominantOrange
import com.example.appcoingecko.ui.theme.SecondOrange
import com.example.traineapp.ui.screen.crypto_list.components.CryptoListItem


@Composable
fun LoadedScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(66.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(if (true) 0f else 360f),
                color = DominantOrange,
                strokeWidth = 6.dp
            )
        }
    }
}



@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWithChips(navController: NavController, viewModel: CryptoListViewModel = viewModel()) {
    val selectedChip by remember { viewModel.liveChipsSelected }


    val state = viewModel.state.value


    Scaffold(
        topBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(117.dp) // Увеличенная высота
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
                        ChipsVal2()
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
                        LoadedScreen()
                    }
                    if(state.error.isNotBlank()) {
                        Log.d("Error", state.error)
                        //ErrorScreen()
                    }
            }
        }
    )
}


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
            .padding(horizontal = 10.dp)
            .height(35.dp),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = SecondOrange.copy(alpha = 0.12f), // Цвет фона при выборе
            selectedLabelColor = SecondOrange,     // Цвет текста при выборе
            containerColor = CryptoGray,     // Цвет фона, когда не выбран
            labelColor = Color.Black              // Цвет текста, когда не выбран
        ),
        border = null,
        shape = RoundedCornerShape(18.dp)
    )
}
@Composable
fun ChipsVal2(viewModel: CryptoListViewModel = viewModel()){
    var selectedChip by remember { viewModel.liveChipsSelected }

    Column {
        Row(modifier = Modifier.padding(top = 16.dp)) {
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
