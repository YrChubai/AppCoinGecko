package com.example.appcoingecko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appcoingecko.ui.theme.AppCoinGeckoTheme
import com.example.traineapp.ui.screen.crypto_detail.CryptoDetailScreen
import com.example.traineapp.ui.screen.crypto_list.CryptoListViewModel
import com.example.traineapp.ui.screen.crypto_list.ToolbarWithChips

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCoinGeckoTheme {
                MainApp()
            }
        }
    }
}


@Composable
fun MainApp(viewModel: CryptoListViewModel = viewModel()
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "crypto_list") {
        composable("crypto_list") {
            ToolbarWithChips(navController)
        }
        composable("crypto_detail/{coinId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("coinId") ?: "tron"
            CryptoDetailScreen(itemId, navController);
        }
    }

}