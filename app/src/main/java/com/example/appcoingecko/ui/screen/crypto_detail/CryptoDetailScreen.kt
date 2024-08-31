package com.example.appcoingecko.ui.screen.crypto_detail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appcoingecko.ui.screen.componets.CircularLoader
import com.example.appcoingecko.ui.screen.componets.ErrorRefresher


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(title: String, onBackClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        },
    )
}
@Composable
fun CryptoDetailScreen(
    id: String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = viewModel()
){
    LaunchedEffect(id) {
        viewModel.loadItems(id)
    }
        val state = viewModel.state.value

        Scaffold(
            topBar = { Surface(shadowElevation = 4.dp){
                MyTopAppBar(title = id, onBackClicked = { navController.navigate("crypto_list")} )
                }
            },
        ) { paddingValues  ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        state.coin?.let {
                        AsyncImage(
                            model = it.image,
                            contentDescription = "",
                        )
                        Text(
                            text = "Описание",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        )
                        val listCategory = it.categories.joinToString(separator = ", ")
                        Text(
                            text = "Категории",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = listCategory,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                        )
                    }
                }
                if(state.isLoading) {
                    CircularLoader()
                }
                if(state.error.isNotBlank()) {
                    Log.d("Error", state.error)
                    ErrorRefresher {
                        viewModel.loadItems(id)
                    }
                }
            }
        }
    }


