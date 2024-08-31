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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appcoingecko.ui.screen.componets.CircularLoader
import com.example.appcoingecko.ui.screen.componets.ErrorRefresher
import com.example.appcoingecko.ui.screen.crypto_list.components.ChipsCurrency
import com.example.appcoingecko.ui.screen.crypto_list.components.CryptoListItem
import com.example.appcoingecko.ui.theme.RedStock


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CryptoListScreen(navController: NavController, viewModel: CryptoListViewModel = viewModel()) {

    val selectedChip by remember { viewModel.liveChipsSelected }
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData = snackbarData,
                        containerColor = RedStock
                    )
                }
            )
        },
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
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                    Row (modifier = Modifier.padding(top = 24.dp)){
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
                            navController.navigate("crypto_detail/${item.id}/${item.name}")
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
                if(state.errorRefresh.isNotBlank()){
                    LaunchedEffect(key1 = state.errorRefresh) {
                        snackbarHostState.showSnackbar(
                            message = "Произошла ошибка при загрузке",
                        )
                    }
                }
            }
        }
    )
}




